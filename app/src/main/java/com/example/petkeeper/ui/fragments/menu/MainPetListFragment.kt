package com.example.petkeeper.ui.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.databinding.PetListFragmentLayoutBinding
import com.example.petkeeper.ui.fragments.AddPetFragment
import com.example.petkeeper.ui.pets.adapter.PetsRvAdapter
import com.example.petkeeper.viewmodel.main.PetViewModelFactory
import com.example.petkeeper.viewmodel.main.PetsViewModel

class MainPetListFragment : Fragment() {

    private lateinit var mBinding: PetListFragmentLayoutBinding
    private lateinit var petsViewModel: PetsViewModel
    private lateinit var database: PetRoomDatabase
    private lateinit var rvAdapter: PetsRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_pet_list_fragment_layout, container, false)

        rvAdapter = PetsRvAdapter(requireContext(), listOf())
        database = PetRoomDatabase(requireContext())
        val repository = PetsRepository(database)
        val factory = PetViewModelFactory(repository)

        petsViewModel = ViewModelProvider(this, factory)[PetsViewModel::class.java]

        with(mBinding) {
            lifecycleOwner = this@MainPetListFragment
            viewModelFromLayout = petsViewModel
            executePendingBindings()
        }

        petsViewModel.petsList?.observe(requireActivity()) {
            rvAdapter.petsList = it
        }

        //Method to add pet
        addPet()

        petsViewModel.getAllPets()
        return mBinding.root
    }

    private fun addPet() {
        mBinding.btnAddPet.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, AddPetFragment()).addToBackStack("PetListFrag").commit()
        }
    }
}


