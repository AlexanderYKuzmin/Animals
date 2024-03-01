package com.kuzmin.animals.dataprovider.remote.mapper

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.kuzmin.animals.dataprovider.remote.model.AnimalDto
import com.kuzmin.animals.dataprovider.remote.model.FactDto
import com.kuzmin.animals.feature.api.model.Animal
import com.kuzmin.animals.feature.api.model.AnimalType
import com.kuzmin.animals.feature.api.model.Fact
import javax.inject.Inject

class DataSnapshotMapper @Inject constructor() {

    fun mapDataSnapshotAnimalsToAnimalsListModel(snapshot: DataSnapshot): List<Animal> {
        Log.d("Db", "Mapper Snapshot: $snapshot")

        return snapshot.children.mapNotNull {
            mapAnimalDtoToAnimal(it.getValue(AnimalDto::class.java))
        }.toList()
    }

    fun mapAnimalsDtoToAnimals(animalsDto: List<AnimalDto>): List<Animal> {
        return emptyList()
    }

    fun mapFactsDataSnapshotToFactsListModel(snapshot: DataSnapshot): List<Fact> {
        return snapshot.children.mapNotNull {
            mapFactDtoToFact(it.getValue(FactDto::class.java))
        }.toList()
    }

    private fun mapAnimalDtoToAnimal(animalDto: AnimalDto?): Animal? {
        if (animalDto == null) return null
        with(animalDto) {
            return Animal(
                id = id,
                info = info,
                nameEn = nameEn,
                nameRu = nameRu,
                urlSound = urlSound,
                type = AnimalType.valueOf(type.uppercase())
            )
        }
    }

    private fun mapFactDtoToFact(factDto: FactDto?): Fact? {
        if (factDto == null) return null
        with(factDto) {
            return Fact(
                animalId = animalId,
                fact = text
            )
        }
    }
}