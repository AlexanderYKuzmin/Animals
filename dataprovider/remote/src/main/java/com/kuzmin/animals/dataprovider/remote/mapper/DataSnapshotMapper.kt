package com.kuzmin.animals.dataprovider.remote.mapper

import com.google.firebase.database.DataSnapshot
import com.kuzmin.animals.feature.home.domain.model.Animal
import javax.inject.Inject

class DataSnapshotMapper @Inject constructor() {

    fun mapDataSnapshotAnimalsToAnimalsListModel(snapshot: DataSnapshot): List<Animal> {
        return snapshot.children.mapNotNull {
            it.getValue(Animal::class.java)
        }.toList()
    }
}