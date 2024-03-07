package com.kuzmin.animals.feature.settings.domain.model

open class SettingsResult {

    object Loading: SettingsResult()

    class Success(
        val quantity: Int,
        val tags: List<String>,
    ) : SettingsResult()

    object SuccessWriting: SettingsResult()

    class Error(val throwable: Throwable): SettingsResult()
}