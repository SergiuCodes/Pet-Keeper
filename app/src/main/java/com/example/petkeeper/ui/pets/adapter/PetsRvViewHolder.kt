package com.example.petkeeper.ui.pets.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.databinding.PetItemLayoutBinding
import com.example.petkeeper.data.database.room.entity.Pet

class PetsRvViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pet: Pet) {
        val binding: PetItemLayoutBinding? = DataBindingUtil.getBinding(view)

        if (binding != null) {
            binding.pet = pet
            binding.executePendingBindings()
        }
    }
}