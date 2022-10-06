package com.example.petkeeper.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.repository.NotificationsRepository
import com.example.petkeeper.databinding.FragmentPetDetailsLayoutBinding
import com.example.petkeeper.viewmodel.addpetscreen.AddPetViewModel
import com.example.petkeeper.viewmodel.notifications.NotificationsViewModel
import com.example.petkeeper.viewmodel.notifications.NotificationsViewModelFactory

class PetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPetDetailsLayoutBinding
    private lateinit var database: PetRoomDatabase
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pet_details_layout, container, false)
        database = PetRoomDatabase(requireContext())
        val repository = NotificationsRepository(database)
        val factory = NotificationsViewModelFactory(repository)
        notificationsViewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]
        

        return binding.root
    }
}