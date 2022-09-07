package com.example.petkeeper.ui.pets.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.databinding.PetItemLayoutBinding
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.tools.AddFragmentListener
import com.example.petkeeper.ui.pets.AddPetFragment

class PetsRvViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pet: Pet) {
        val binding: PetItemLayoutBinding? = DataBindingUtil.getBinding(view)

        if (binding != null) {
            binding.pet = pet
            binding.executePendingBindings()
        }
    }
}