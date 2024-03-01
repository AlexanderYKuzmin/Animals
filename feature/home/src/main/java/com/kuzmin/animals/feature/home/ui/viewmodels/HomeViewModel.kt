package com.kuzmin.animals.feature.home.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.home.R
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.AnimalType.*
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.domain.usecases.GetDbTestUseCase
import com.kuzmin.animals.feature.home.ui.model.ParentItem
import com.kuzmin.animals.feature.home.ui.model.ParentItemFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllAnimalsUseCase: GetAllAnimalsUseCase,
    private val parentFactory: ParentItemFactory
) : ViewModel() {

    private val _animalsByTypes = MutableLiveData<Result>()
    val animalsByTypes: LiveData<Result> get() = _animalsByTypes

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _animalsByTypes.postValue(Result.Error(throwable))
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

    fun prepareUiData(animalsByTypes: Map<AnimalType, List<Animal>>): List<ParentItem> {
        val parents = mutableListOf<ParentItem>()

        animalsByTypes.entries.forEach {(k, v) ->
            parents.add( parentFactory.createParentItem(k, v))
        }

        /*for ((k, v) in animalsByTypes) {
            //val children = v.map { ChildItem(it.nameRu) }
            parents.add( parentFactory.createParentItem(k, v))
                *//*with(parentFactory) {
                    when(k) {
                        BEAST -> { createParentItem(k, v)}
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
                }*//*
        }*/
        return parents
    }
}