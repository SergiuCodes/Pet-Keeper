package com.example.petkeeper.tools

import com.example.petkeeper.data.database.room.entity.Pet

interface AddFragmentListener {
    fun onAddButtonClicked(pet: Pet)
}