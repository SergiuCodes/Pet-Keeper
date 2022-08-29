package com.example.petkeeper.tools

import com.example.petkeeper.data.database.room.entity.Pet

interface AddDialogListener {
    fun onAddButtonClicked(pet: Pet)
}