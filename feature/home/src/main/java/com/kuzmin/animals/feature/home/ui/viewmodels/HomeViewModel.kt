package com.kuzmin.animals.feature.home.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.espresso.idling.CountingIdlingResource
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.ui.model.ParentItem
import com.kuzmin.animals.feature.home.ui.model.ParentItemFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Optional
import javax.inject.Inject
import kotlin.jvm.optionals.getOrNull

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAnimalsUseCase: GetAllAnimalsUseCase,
    private val parentFactory: ParentItemFactory,
    private val idleResource: Optional<CountingIdlingResource>

) : ViewModel() {

    private val _animalsByTypes = MutableLiveData<Result>()
    val animalsByTypes: LiveData<Result> get() = _animalsByTypes

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _animalsByTypes.postValue(Result.Error(throwable))
    }

    fun getAllAnimals() {
        _animalsByTypes.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            idleResource.getOrNull()?.increment()
            val animals = getAllAnimalsUseCase()

            _animalsByTypes.postValue(
                Result.Success(
                    animals.groupBy { it.type }
                )
            )
            idleResource.getOrNull()?.decrement()
        }
    }

    fun prepareUiData(animalsByTypes: Map<AnimalType, List<Animal>>): List<ParentItem> {
        val parents = mutableListOf<ParentItem>()

        animalsByTypes.entries.forEach {(k, v) ->
            parents.add( parentFactory.createParentItem(k, v))
        }
        return parents
    }
}