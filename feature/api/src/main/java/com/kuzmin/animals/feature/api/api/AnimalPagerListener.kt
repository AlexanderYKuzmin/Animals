package com.kuzmin.animals.feature.api.api

interface AnimalPagerListener {
    fun onAnimalPagerActivated(isActive: Boolean, title: String?)
}