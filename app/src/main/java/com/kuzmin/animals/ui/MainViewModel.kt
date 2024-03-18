package com.kuzmin.animals.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuzmin.animals.common.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _appState = MutableLiveData(AppState())
    private val appState: AppState get() = _appState.value!!

    fun observeAppState(lifecycleOwner: LifecycleOwner, update: (appState: AppState) -> Unit) {
        _appState.observe(lifecycleOwner) { update(it) }
    }

    fun handleAnimalPagerActivated(isPagingActivated: Boolean, animalName: String?) {
        _appState.value = appState.copy(
            isAnimalPagingActivated = isPagingActivated,
            isBottomBarVisible = !isPagingActivated,
            chosenAnimal = animalName,
            title = animalName ?: AppConstants.appName
        )
    }
}

data class AppState(
    val title: String = AppConstants.appName,
    val chosenAnimal: String? = null,
    val isBottomBarVisible: Boolean = true,
    val isAnimalPagingActivated: Boolean = false
)