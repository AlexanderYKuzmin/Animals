package com.kuzmin.animals.feature.home.domain.model

import android.net.Uri
import com.kuzmin.animals.feature.api.model.AnimalPhoto

sealed class FlickrResult {
    data object Loading : FlickrResult()
    class Success(
        val info: String,
        val photos: List<AnimalPhoto>,
        val facts: List<com.kuzmin.animals.feature.api.model.Fact>,
        val mediaUrl: Uri
    ): FlickrResult()
    class Error(val throwable: Throwable): FlickrResult()
}