package com.kuzmin.animals.feature.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.AnimalType
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetDbTestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAnimalsUseCase: GetAllAnimalsUseCase,
    private val getDbTestUseCase: GetDbTestUseCase
) : ViewModel() {

    private val _animalsByTypes = MutableLiveData<Result>()
    val animalsByTypes: LiveData<Result> get() = _animalsByTypes

    /*private val _animals = MutableLiveData<Result>()
    val animals: LiveData<Result> get() = _animals*/

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _animalsByTypes.postValue(Result.Error(throwable))
    }

    fun getAllAnimals() {
        viewModelScope.launch(Dispatchers.IO) {
            val animals = getAllAnimalsUseCase()
            _animalsByTypes.postValue(
                Result.Success(
                    animals.groupBy { it.type }
                )
            )
        }
    }

    fun getDbTest() {
        Log.d("Db", "get Db from view model")
        viewModelScope.launch {
            println("FireDatabase is ${getDbTestUseCase()}")
        }

    }
}