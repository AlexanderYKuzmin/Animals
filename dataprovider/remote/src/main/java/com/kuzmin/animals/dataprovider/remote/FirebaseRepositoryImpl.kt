package com.kuzmin.animals.dataprovider.remote

import android.net.Uri
import android.util.Log
import com.kuzmin.animals.core.network.FirebaseService
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.feature.home.api.FirebaseRepository
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.Fact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val mapper: DataSnapshotMapper,
    private val firebaseService: FirebaseService
) : FirebaseRepository {

    override suspend fun getAllAnimals(): List<Animal> {
        Log.d("Db", "Repository getAllAnimals")
        return mapper.mapDataSnapshotAnimalsToAnimalsListModel(
            firebaseService.getAllAnimals().await()
        )
    }

    override suspend fun getFactsByAnimalId(id: Int): List<Fact> {
        Log.d("Db", "Repository getFacts id: $id")
        return mapper.mapFactsDataSnapshotToFactsListModel(
            firebaseService.getFactsByAnimalId(id).await()
        )
    }

    override suspend fun getMediaUrl(path: String): Uri {
        Log.d("Db", "Repository path to sound: $path")
        return firebaseService.getMediaUrlByName(path).await()
    }

    override suspend fun getDbTest(): String {
        Log.d("Db", "Repository: service getDbTest")
        return firebaseService.test()
    }
}