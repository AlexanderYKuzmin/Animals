package com.kuzmin.animals.feature.home.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.home.domain.model.Fact
import com.kuzmin.animals.feature.home.domain.model.FlickrResult
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetFactsByAnimalId
import com.kuzmin.animals.feature.home.domain.usecases.GetPhotoUrlListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val getPhotoUrlListUseCase: GetPhotoUrlListUseCase,
    private val getFactsByAnimalId: GetFactsByAnimalId
): ViewModel() {

    private val _photos = MutableLiveData<FlickrResult>()
    val photos: LiveData<FlickrResult> get() = _photos

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _photos.postValue(FlickrResult.Error(throwable))
    }
    fun getPhotoUrls(tag: String, id: Int) {
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
    }
}