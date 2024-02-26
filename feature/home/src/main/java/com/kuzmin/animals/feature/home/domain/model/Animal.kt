package com.kuzmin.animals.feature.home.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Animal(
    val id: Int,

    val info: String,

    val nameEn: String,

    val nameRu: String,

    val urlSound: String,

    val type: AnimalType
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "Nothing",
        parcel.readString() ?: "No name",
        parcel.readString() ?: "No name",
        parcel.readString() ?: "",
        AnimalType.valueOf(parcel.readString()?.uppercase() ?: "NO_TYPE")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(info)
        parcel.writeString(nameEn)
        parcel.writeString(nameRu)
        parcel.writeString(urlSound)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}