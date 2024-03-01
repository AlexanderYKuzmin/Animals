package com.kuzmin.animals.feature.home.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.common.model.AnimalPhoto
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.AnimalType
import com.kuzmin.animals.feature.home.domain.model.FlickrRequest
import com.kuzmin.animals.feature.home.domain.model.FlickrResult
import com.kuzmin.animals.feature.home.domain.model.MediaState
import com.kuzmin.animals.feature.home.domain.usecases.AddExcludeUseCase
import com.kuzmin.animals.feature.home.domain.usecases.AddFavoriteUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetExcludedUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetFactsByAnimalIdUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetMediaUrlUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetPhotoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
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
    private val mediaService: MediaService
): ViewModel() {

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
                val blacklist = mutableListOf<String>()
                launch {
                    blacklist.addAll(getExcludedUseCase.getExcludedIdsByName(animal.nameEn))
                }.join()
                getPhotoList(animal.nameEn, blacklist)
            }
            val factsDef = async { getFactsById(animal.id) }
            val mediaUrlDef = async { getMediaUrl(animal.type, animal.nameEn, pathPattern) }

            _photos.postValue(FlickrResult.Success(
                animal.info,
                photosDef.await(),
                factsDef.await(),
                mediaUrlDef.await()
            ))
        }
    }

    private suspend fun getPhotoList(tag: String, blacklist: List<String>): List<AnimalPhoto> {
        return getPhotoListUseCase(
            FlickrRequest(
                listOf(tag),
                blacklist
            )
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
            when(command) {
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