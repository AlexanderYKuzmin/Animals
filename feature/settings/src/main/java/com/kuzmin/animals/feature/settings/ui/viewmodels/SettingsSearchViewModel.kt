package com.kuzmin.animals.feature.settings.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.settings.domain.model.SettingsResult
import com.kuzmin.animals.feature.settings.domain.usecases.AddQuantityUseCase
import com.kuzmin.animals.feature.settings.domain.usecases.AddTagsUseCase
import com.kuzmin.animals.feature.settings.domain.usecases.GetSearchQuantityUseCase
import com.kuzmin.animals.feature.settings.domain.usecases.GetTagsByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsSearchViewModel @Inject constructor(
    private val getTagsByTypeUseCase: GetTagsByTypeUseCase,
    private val getSearchQuantityUseCase: GetSearchQuantityUseCase,
    private val addTagsUseCase: AddTagsUseCase,
    private val addQuantityUseCase: AddQuantityUseCase
) : ViewModel() {

    private val _searchAnimalSettings = MutableLiveData<SettingsResult>()
    val searchAnimalSettings: LiveData<SettingsResult> get() = _searchAnimalSettings

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _searchAnimalSettings.postValue(SettingsResult.Error(throwable))
    }


    fun handleSelectedType(type: AnimalType) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {

            val quantityDef = async { getSearchQuantityUseCase() }

            val tagsDef = async { getTagsByTypeUseCase(type.name.lowercase()) }

            _searchAnimalSettings.postValue(
                SettingsResult.Success(
                    quantityDef.await(),
                    tagsDef.await()
                )
            )
        }
    }

    fun handleSaveSearchData(type: AnimalType, tags: List<String>, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val quantityJob = launch { addQuantityUseCase(quantity) }
            val tagsJob = launch { addTagsUseCase(type, tags) }

            quantityJob.join()
            tagsJob.join()
            _searchAnimalSettings.postValue(SettingsResult.SuccessWriting)
        }
    }
}