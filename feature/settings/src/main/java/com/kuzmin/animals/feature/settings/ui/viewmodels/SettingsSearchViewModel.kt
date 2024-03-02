package com.kuzmin.animals.feature.settings.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.kuzmin.animals.feature.settings.domain.usecases.AddTagUseCase
import com.kuzmin.animals.feature.settings.domain.usecases.GetTagsByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsSearchViewModel @Inject constructor(
    private val getTagsByTypeUseCase: GetTagsByTypeUseCase,
    private val addTagUseCase: AddTagUseCase
) : ViewModel() {

    //private val _
}