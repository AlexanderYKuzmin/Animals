package com.kuzmin.animals.feature.home.domain.model

sealed class MediaState {
    data object Completed : MediaState()
}