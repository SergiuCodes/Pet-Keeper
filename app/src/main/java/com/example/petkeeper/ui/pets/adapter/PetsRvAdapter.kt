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
import com.example.petkeeper.ui.fragments.notifications.AddNotificationFragment

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

            val bundleForDetailsFragment: Bundle = Bundle()
            bundleForDetailsFragment.putString("petage", pet.petDateOfBirth)
            bundleForDetailsFragment.putString("petname", pet.petName)
            bundleForDetailsFragment.putString("petspecies", pet.petSpecies)
            bundleForDetailsFragment.putParcelable("petbitmap", pet.petImage)

            onItemClickListener?.let {
                it(pet)
            }
            val activity = context as AppCompatActivity
            val petDetailsFragment = PetDetailsFragment()

            petDetailsFragment.arguments = bundleForDetailsFragment
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, petDetailsFragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return petsList.size
    }
}