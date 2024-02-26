package com.kuzmin.animals.feature.home.domain.model

import android.net.Uri

sealed class FlickrResult {
    data object Loading : FlickrResult()
    class Success(
        val urls: List<String>,
        val facts: List<Fact>,
        val mediaUrl: Uri
    ): FlickrResult()
    class Error(val throwable: Throwable): FlickrResult()
}