package com.example.petkeeper.tools.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.ui.pets.adapter.PetsRvAdapter

@BindingAdapter(value = ["setPetsList"])

fun setPetsRecyclerView(
    nRecyclerView: RecyclerView,
    nPetsList: List<Pet>?
) {
    if (nPetsList != null) {
        val mPetsAdapter = PetsRvAdapter(nRecyclerView.context)
        nRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = mPetsAdapter
        }
        mPetsAdapter.submitPetsList(nPetsList)
    }
}