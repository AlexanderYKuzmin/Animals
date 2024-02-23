package com.kuzmin.animals.feature.home.domain.model

sealed class FlickrResult {
    data object Loading : FlickrResult()
    class Success(val urls: List<String>, val facts: List<Fact>): FlickrResult()
    class Error(val throwable: Throwable): FlickrResult()
}