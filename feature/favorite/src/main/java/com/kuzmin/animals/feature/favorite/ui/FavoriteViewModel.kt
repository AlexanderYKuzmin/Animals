package com.kuzmin.animals.feature.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuzmin.animals.feature.favorite.domain.usecases.FavoritesResult
import com.kuzmin.animals.feature.favorite.domain.usecases.FavoritesResult.*
import com.kuzmin.animals.feature.favorite.domain.usecases.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _favorites.postValue(Error(throwable))
    }

    private val _favorites = MutableLiveData<FavoritesResult>()
    val favorites: LiveData<FavoritesResult> get() = _favorites

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _favorites.postValue(Loading)

            _favorites.postValue(
                Success(getAllFavoritesUseCase())
            )
        }
    }
}