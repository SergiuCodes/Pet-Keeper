package com.example.petkeeper.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentPetDetailsLayoutBinding
import com.example.petkeeper.ui.pets.adapter.PetsRvAdapter

class PetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPetDetailsLayoutBinding
    private lateinit var rvAdapter: PetsRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_pet_details_layout,
            container,
            false
        )

        with(binding) {
            lifecycleOwner = this@PetDetailsFragment
            executePendingBindings()
        }

        rvAdapter = PetsRvAdapter(requireContext())
        val bundle: Bundle? = this.arguments
        val petAgeString: String? = bundle?.getString("petage")
        val petNameString: String? = bundle?.getString("petname")
        val petSpecies: String? = bundle?.getString("petspecies")
        val petBitmap =
            arguments?.getParcelable<Bitmap>("petbitmap")

        binding.tvPetName.text = petNameString
        binding.tvPetBirth.text = petAgeString
        binding.tvPetSpecies.text = petSpecies
        binding.petImage.setImageBitmap(petBitmap)

//        rvAdapter.setOnItemClickListener {
//            binding.tvPetBirth.text = it.petDateOfBirth
//            binding.tvPetName.text = it.petName
//            binding.tvPetSpecies.text = it.petSpecies
//            binding.petImage.setImageBitmap(it.petImage)
//        }

        return binding.root
    }
}