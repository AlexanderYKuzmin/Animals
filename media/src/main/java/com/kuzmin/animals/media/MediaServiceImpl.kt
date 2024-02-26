package com.kuzmin.animals.media

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.kuzmin.animals.feature.home.api.MediaService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.PrivateKey
import javax.inject.Inject

class MediaServiceImpl @Inject constructor(
    private val mediaPlayer: MediaPlayer,
    @ApplicationContext private val appContext: Context
) : MediaService {

    override suspend fun play(uri: Uri, update: () -> Unit) {
        mediaPlayer.apply {
            setDataSource(appContext, uri)
            setVolume(0f, 50f)
            setOnCompletionListener {
                update()
                stop()
            }
            prepare()
            start()
        }
    }

    override suspend fun stop() {
        mediaPlayer.apply {
            stop()
            reset()
        }
    }

    override suspend fun pause() {
        TODO("Not yet implemented")
    }

}