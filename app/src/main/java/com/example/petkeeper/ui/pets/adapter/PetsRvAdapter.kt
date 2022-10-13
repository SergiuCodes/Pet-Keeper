package com.example.petkeeper.ui.pets.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.ui.fragments.PetDetailsFragment

class PetsRvAdapter(private val context: Context, var petsList: List<Pet> = ArrayList()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mRowBinding: ViewDataBinding
    private var onItemClickListener: ((Pet) -> Unit)? = null

    fun submitPetsList(nList: List<Pet>) {
        petsList = nList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Pet) -> Unit) {
        onItemClickListener = listener
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

        val pet = petsList[position]
        (holder as PetsRvViewHolder).bind(pet)



        holder.itemView.rootView.setOnClickListener {

            val bundle: Bundle = Bundle()
            bundle.putString("petage", pet.petDateOfBirth)
            bundle.putString("petname", pet.petName)
            bundle.putString("petspecies", pet.petSpecies)
            bundle.putParcelable("petbitmap", pet.petImage)

            onItemClickListener?.let {
                it(pet)
            }
            val activity = context as AppCompatActivity
            val notificationsManagerFragment = PetDetailsFragment()
            notificationsManagerFragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, notificationsManagerFragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return petsList.size
    }
}