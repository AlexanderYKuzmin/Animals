package com.kuzmin.animals.feature.home.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.AnimalType
import com.kuzmin.animals.feature.home.domain.model.FlickrResult
import com.kuzmin.animals.feature.home.domain.model.MediaState
import com.kuzmin.animals.feature.home.domain.usecases.GetFactsByAnimalIdUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetMediaUrlUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetPhotoUrlListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val getPhotoUrlListUseCase: GetPhotoUrlListUseCase,
    private val getFactsByAnimalIdUseCase: GetFactsByAnimalIdUseCase,
    private val getMediaUrlUseCase: GetMediaUrlUseCase,
    private val mediaService: MediaService
): ViewModel() {

    private val _photos = MutableLiveData<FlickrResult>()
    val photos: LiveData<FlickrResult> get() = _photos

    private val _mediaPlayerState = MutableLiveData<MediaState>()
    val mediaPlayerState: LiveData<MediaState> get() = _mediaPlayerState

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _photos.postValue(FlickrResult.Error(throwable))
    }
    /*fun getPhotoUrls(tag: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            var urls: List<String>? = null
            var facts: List<Fact>? = null

            val photoJob = launch {
                urls = getPhotoUrlListUseCase(tag)
            }
            val factJob = launch {
                facts = getFactsByAnimalId(id)
            }
            factJob.join()
            photoJob.join()

            if (urls != null && facts != null) {
                _photos.postValue(
                    FlickrResult.Success(
                        urls!!, facts!!
                    )
                )
            } else {
                throw RuntimeException("Wrong job data!")
            }
        }
    }*/
    fun getAnimalResources(animal: Animal, pathPattern: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val photosDef = async { getPhotoUrls(animal.nameEn) }
            val factsDef = async { getFactsById(animal.id) }
            val mediaUrlDef = async { getMediaUrl(animal.type, animal.nameEn, pathPattern) }

            _photos.postValue(FlickrResult.Success(
                photosDef.await(),
                factsDef.await(),
                mediaUrlDef.await()
            ))
        }
    }

    private suspend fun getPhotoUrls(tag: String) = getPhotoUrlListUseCase(tag)
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

}