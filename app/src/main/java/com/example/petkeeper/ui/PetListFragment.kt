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
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.databinding.PetListFragmentLayoutBinding
import com.example.petkeeper.tools.AddDialogListener
import com.example.petkeeper.viewmodel.PetsViewModel

class PetListFragment() : Fragment() {

    private lateinit var mBinding: PetListFragmentLayoutBinding
    private lateinit var petsViewModel: PetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.pet_list_fragment_layout, container, false)

        petsViewModel = ViewModelProvider(this).get(PetsViewModel::class.java)

        with(mBinding) {
            lifecycleOwner = this@PetListFragment
            viewModelFromLayout = petsViewModel
            executePendingBindings()
        }

        mBinding.btnAddPet.setOnClickListener {
            AddPetDialog(
                this.requireContext(),
                object : AddDialogListener {
                    override fun onAddButtonClicked(pet: Pet) {
                        petsViewModel.insertPet(pet)
                    }
                }).show()
        }
        petsViewModel.insertPet(Pet("Test","Test","test",1))
        petsViewModel.getAllPets()
        Log.d("PetFrag","Viewmodel list " + petsViewModel.petsList.toString())

//        petsViewModel.getAllPets()?.observe(viewLifecycleOwner, Observer {
//            PetsRvAdapter(requireContext()).petsList = it
//        })
        return mBinding.root
    }
}