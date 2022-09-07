package com.example.petkeeper.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.databinding.PetListFragmentLayoutBinding
import com.example.petkeeper.tools.AddFragmentListener
import com.example.petkeeper.ui.pets.AddPetFragment
import com.example.petkeeper.ui.pets.adapter.PetsRvAdapter
import com.example.petkeeper.viewmodel.PetViewModelFactory
import com.example.petkeeper.viewmodel.PetsViewModel

private const val REQUEST_CODE = 46

class PetListFragment : Fragment() {

    private lateinit var mBinding: PetListFragmentLayoutBinding
    private lateinit var petsViewModel: PetsViewModel
    private lateinit var database: PetRoomDatabase
    private lateinit var rvAdapter: PetsRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.pet_list_fragment_layout, container, false)

        rvAdapter = PetsRvAdapter(requireContext(), listOf())
        database = PetRoomDatabase(requireContext())
        val repository = PetsRepository(database)
        val factory = PetViewModelFactory(repository)


        petsViewModel = ViewModelProvider(this, factory)[PetsViewModel::class.java]

        with(mBinding) {
            lifecycleOwner = this@PetListFragment
            viewModelFromLayout = petsViewModel
            executePendingBindings()
        }

        petsViewModel.petsList?.observe(requireActivity()) {
            rvAdapter.petsList = it
            rvAdapter.notifyDataSetChanged()
        }

        //Adding a new pet into the list
        mBinding.btnAddPet.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddPetFragment(object : AddFragmentListener {
                    override fun onAddButtonClicked(pet: Pet) {
                        Log.d("PetListFragment", "After clicked save $pet")
                        petsViewModel.insertPet(pet)
                    }
                })).addToBackStack("PetListFrag").commit()
        }

        petsViewModel.getAllPets()
        return mBinding.root

    }
}


