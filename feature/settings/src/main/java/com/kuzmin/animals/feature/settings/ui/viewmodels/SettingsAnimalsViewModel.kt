package com.kuzmin.animals.feature.settings.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.kuzmin.animals.feature.settings.domain.usecases.AddCustomAnimalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsAnimalsViewModel @Inject constructor(
    private val addCustomAnimalUseCase: AddCustomAnimalUseCase,

    ) : ViewModel() {
}