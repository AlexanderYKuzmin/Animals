package com.kuzmin.animals.feature.api.api

interface PrefManager {
    suspend fun writeData(quantity: Int)

    suspend fun readData(): Int
}