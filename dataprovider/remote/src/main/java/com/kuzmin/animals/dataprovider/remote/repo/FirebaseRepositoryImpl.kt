package com.kuzmin.animals.dataprovider.remote.repo

import android.net.Uri
import android.util.Log
import com.kuzmin.animals.core.network.FirebaseService
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.feature.api.api.FirebaseRepository
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.Fact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val mapper: DataSnapshotMapper,
    private val firebaseService: FirebaseService
) : FirebaseRepository {

    override suspend fun getAllAnimals(): List<Animal> {
        return mapper.mapDataSnapshotAnimalsToAnimalsListModel(
            firebaseService.getAllAnimals().await()
        )
    }

    override suspend fun getFactsByAnimalId(id: Int): List<Fact> {
        return mapper.mapFactsDataSnapshotToFactsListModel(
            firebaseService.getFactsByAnimalId(id).await()
        )
    }

    override suspend fun getMediaUrl(path: String): Uri {
        return firebaseService.getMediaUrlByName(path).await()
    }
}