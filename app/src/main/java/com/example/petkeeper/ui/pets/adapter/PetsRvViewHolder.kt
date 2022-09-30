package com.example.petkeeper.ui.pets.adapter

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petkeeper.R
import com.example.petkeeper.databinding.PetItemLayoutBinding
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.ui.fragments.notifications.AddNotificationFragment

class PetsRvViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(pet: Pet) {
        val binding: PetItemLayoutBinding? = DataBindingUtil.getBinding(view)

        if (binding != null) {
            binding.pet = pet

            binding.btnManageNotifications.setOnClickListener(object: View.OnClickListener{
                override fun onClick(view: View?) {
                    val activity = view!!.context as AppCompatActivity
                    val notificationsManagerFragment = AddNotificationFragment()
                    activity.supportFragmentManager.beginTransaction().replace(R.id.container, notificationsManagerFragment).addToBackStack(null).commit()
                }
            })
            binding.executePendingBindings()
        }
    }
}