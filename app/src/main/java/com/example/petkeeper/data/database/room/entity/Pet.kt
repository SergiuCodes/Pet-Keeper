package com.example.petkeeper.data.database.room.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets_table")

data class Pet(
    @ColumnInfo(name = "petName")
    val petName: String,
    @ColumnInfo(name = "petSpecies")
    val petSpecies: String,
    @ColumnInfo(name = "petDateOfBirth")
    val petDateOfBirth: String,
    @ColumnInfo(name = "petImage")
    var petImage: Bitmap,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)