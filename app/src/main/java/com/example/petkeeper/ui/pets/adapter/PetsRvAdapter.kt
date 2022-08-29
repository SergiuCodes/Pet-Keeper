package com.example.petkeeper.ui.pets.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Pet

class PetsRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var petsList: List<Pet> = ArrayList()
    private lateinit var mRowBinding: ViewDataBinding

    fun submitPetsList(nList: List<Pet>){
        petsList = nList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.pet_item_layout,
            parent,
            false
        )
        return PetsRvViewHolder(mRowBinding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val petItem = petsList[position]
        (holder as PetsRvViewHolder).bind(petItem)
    }

    override fun getItemCount(): Int {
        return petsList.size
    }
}