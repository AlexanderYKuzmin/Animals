package com.kuzmin.animals.feature.api.model

data class FlickrRequest(
    val tags: List<String>,

    val blacklist: List<String>,

    val quantity: Int = 20
)
