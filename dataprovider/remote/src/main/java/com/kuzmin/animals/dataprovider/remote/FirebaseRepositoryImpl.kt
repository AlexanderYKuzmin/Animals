package com.kuzmin.animals.dataprovider.remote

import android.util.Log
import com.kuzmin.animals.core.network.FirebaseService
import com.kuzmin.animals.dataprovider.remote.mapper.DataSnapshotMapper
import com.kuzmin.animals.feature.home.api.FirebaseRepository
import com.kuzmin.animals.feature.home.domain.model.Animal
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val mapper: DataSnapshotMapper,
    private val firebaseService: FirebaseService
) : FirebaseRepository {

    override suspend fun getAllAnimals(): List<Animal> {
        var animals = emptyList<Animal>()
        firebaseService.getAllAnimals().addOnSuccessListener {
            animals = mapper.mapDataSnapshotAnimalsToAnimalsListModel(it)
        }
        return animals
    }

    override suspend fun getDbTest(): String {
        Log.d("Db", "Repository: service getDbTest")
        return firebaseService.test()
    }
}