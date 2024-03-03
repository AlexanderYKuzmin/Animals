package com.kuzmin.animals.feature.home.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.api.model.AnimalPhoto
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.FlickrRequest
import com.kuzmin.animals.feature.home.domain.model.FlickrResult
import com.kuzmin.animals.feature.home.domain.model.MediaState
import com.kuzmin.animals.feature.home.domain.usecases.AddExcludeUseCase
import com.kuzmin.animals.feature.home.domain.usecases.AddFavoriteUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetExcludedUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetFactsByAnimalIdUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetMediaUrlUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetPhotoListUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetSearchQuantityUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetTagsByAnimalTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase,
    private val getFactsByAnimalIdUseCase: GetFactsByAnimalIdUseCase,
    private val getMediaUrlUseCase: GetMediaUrlUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val addExcludeUseCase: AddExcludeUseCase,
    private val getExcludedUseCase: GetExcludedUseCase,
    private val getTagsByAnimalTypeUse: GetTagsByAnimalTypeUseCase,
    private val getSearchQuantityUseCase: GetSearchQuantityUseCase,
    private val mediaService: MediaService
) : ViewModel() {

    private val _photos = MutableLiveData<FlickrResult>()
    val photos: LiveData<FlickrResult> get() = _photos

    private val _mediaPlayerState = MutableLiveData<MediaState>()
    val mediaPlayerState: LiveData<MediaState> get() = _mediaPlayerState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _photos.postValue(FlickrResult.Error(throwable))
    }

    fun getAnimalResources(animal: Animal, pathPattern: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

            val photosDef = async {

                val blacklistDef = async {
                    getExcludedUseCase(animal.nameEn)
                }
                val tagListDef = async {
                    getTagsByAnimalTypeUse(animal.type.name.lowercase())
                }
                val searchQuantityDef = async { getSearchQuantityUseCase() }

                getPhotoList(animal, tagListDef.await(), blacklistDef.await(), searchQuantityDef.await())
            }
            val factsDef = async { getFactsById(animal.id) }
            val mediaUrlDef = async { getMediaUrl(animal.type, animal.nameEn, pathPattern) }

            _photos.postValue(
                FlickrResult.Success(
                    animal.info,
                    photosDef.await(),
                    factsDef.await(),
                    mediaUrlDef.await()
                )
            )
        }
    }

    private suspend fun getPhotoList(
        animal: Animal,
        tags: List<String>,
        blacklist: List<String>,
        searchQuantity: Int
    ): List<AnimalPhoto> {
        return getPhotoListUseCase(
            FlickrRequest(animal, tags, blacklist, searchQuantity)
        )
    }

    private suspend fun getFactsById(id: Int) = getFactsByAnimalIdUseCase(id)

    private suspend fun getMediaUrl(type: AnimalType, name: String, pathPattern: String): Uri {
        Log.d("Db", "get media url. Type: ${type.name}")
        Log.d("Db", "get media url. Type to lowercase: ${type.name.lowercase()}")
        val folderName = type.name.lowercase()
        val fileName = name.lowercase()
        return getMediaUrlUseCase(String.format(pathPattern, folderName, fileName))
    }

    fun handleMedia(mediaUri: Uri = Uri.EMPTY, command: String) {
        viewModelScope.launch {
            when (command) {
                MediaService.PLAY -> mediaService.play(mediaUri) {
                    _mediaPlayerState.postValue(MediaState.Completed)
                }

                MediaService.STOP -> mediaService.stop()
            }
        }
    }

    fun saveAnimalAsFavorite(photo: AnimalPhoto) {
        viewModelScope.launch(Dispatchers.IO) { addFavoriteUseCase(photo) }
    }

    fun saveAnimalAsExcluded(photo: AnimalPhoto) {
        viewModelScope.launch(Dispatchers.IO) { addExcludeUseCase(photo) }
    }
}