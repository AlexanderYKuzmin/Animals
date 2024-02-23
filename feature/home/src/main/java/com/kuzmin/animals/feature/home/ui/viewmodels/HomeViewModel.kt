package com.kuzmin.animals.feature.home.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.AnimalType
import com.kuzmin.animals.feature.home.domain.model.AnimalType.*
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetDbTestUseCase
import com.kuzmin.animals.feature.home.ui.model.ParentItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAnimalsUseCase: GetAllAnimalsUseCase,
    private val getDbTestUseCase: GetDbTestUseCase
) : ViewModel() {

    private val _animalsByTypes = MutableLiveData<Result>()
    val animalsByTypes: LiveData<Result> get() = _animalsByTypes

    /*private val _animalsByTypes = MutableLiveData<Result>()
    val animalsByTypes: LiveData<Result> get() = _animalsByTypes*/


    /*private val _animals = MutableLiveData<Result>()
    val animals: LiveData<Result> get() = _animals*/

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _animalsByTypes.postValue(Result.Error(throwable))
    }

    init {
        //getAllAnimals()
    }

    fun getAllAnimals() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
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

    fun prepareUiData(animalsByTypes: Map<AnimalType, List<Animal>>): List<ParentItem> {
        val parents = mutableListOf<ParentItem>()
        for ((k, v) in animalsByTypes) {
            //val children = v.map { ChildItem(it.nameRu) }
            parents.add(
                when(k) {
                    BEAST -> {
                        ParentItem(
                            image = R.drawable.squirrel_2,
                            title = k,
                            children = v,
                        )
                    }
                    BIRD -> {
                        ParentItem(
                            image = R.drawable.crow_2,
                            title = k,
                            children = v
                        )
                    }
                    INSECT -> {
                        ParentItem(
                            image = R.drawable.bug_4,
                            title = k,
                            children = v
                        )
                    }
                    MARINE_LIFE -> {
                        ParentItem(
                            image = R.drawable.fish_4,
                            title = k,
                            children = v
                        )
                    }

                    else -> {
                        ParentItem(
                            image = R.drawable.bug_4,
                            title = k,
                            children = v
                        )
                    }
                }
            )
        }
        return parents
    }
}