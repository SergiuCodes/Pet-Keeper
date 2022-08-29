package com.example.petkeeper.data.database.room.entity

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
//    @ColumnInfo(name = "petImage")
//    val petImage: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0
)