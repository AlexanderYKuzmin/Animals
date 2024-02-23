package com.kuzmin.animals.dataprovider.remote.mapper

import com.kuzmin.animals.dataprovider.remote.model.AnimalDto
import com.kuzmin.animals.feature.home.domain.model.Animal
import com.kuzmin.animals.feature.home.domain.model.AnimalType
import javax.inject.Inject

class DtoToModelMapper @Inject constructor(

) {
    fun mapAnimalDtoToAnimal(animalDto: AnimalDto): Animal {
        with(animalDto) {
            return Animal(
                id = id,
                info = info,
                nameEn = nameEn,
                nameRu = nameRu,
                type = AnimalType.valueOf(type),
                urlSound = urlSound
            )
        }
    }
}