package com.kuzmin.animals.dataprovider.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kuzmin.animals.dataprovider.local.datastore.UserScheme.QUANTITY
import com.kuzmin.animals.feature.api.api.PrefManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PrefManagerImpl @Inject constructor(
    @ApplicationContext appContext: Context
) : PrefManager {
    val dataStore = appContext.dataStore

    override suspend fun writeData(quantity: Int) {
        dataStore.edit { prefs ->
            prefs[QUANTITY] = quantity.toString()
        }
    }
    override suspend fun readData(): Int {
        return dataStore.data.map {
            it[QUANTITY]?.toInt() ?: 20
        }.first()
    }
}