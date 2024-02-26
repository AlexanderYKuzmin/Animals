package com.kuzmin.animals.feature.home.api

import android.net.Uri

interface MediaService {

    suspend fun play(uri: Uri, update: () -> Unit)

    suspend fun stop()

    suspend fun pause()

    companion object {
        const val PLAY = "play"
        const val STOP = "stop"
        const val PAUSE = "pause"
    }
}