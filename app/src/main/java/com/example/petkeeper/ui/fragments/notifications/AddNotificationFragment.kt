package com.example.petkeeper.ui.fragments.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentAddNotificationLayoutBinding
import com.example.petkeeper.ui.MainActivity

class AddNotificationFragment : Fragment() {

    private lateinit var binding: FragmentAddNotificationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_notification_layout, container, false)

        with(binding){
            lifecycleOwner = this@AddNotificationFragment
            executePendingBindings()
        }
        binding.btnMeal.setOnClickListener{
            val activity = requireContext() as MainActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, MealNotificationFragment()).addToBackStack(null).commit()
        }

        binding.btnDeworming.setOnClickListener{
            val activity = requireContext() as MainActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, DewormingNotificationFragment()).addToBackStack(null).commit()
        }

        binding.btnMedicine.setOnClickListener{
            val activity = requireContext() as MainActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, MedicineNotificationFragment()).addToBackStack(null).commit()
        }

        binding.btnVaccination.setOnClickListener{
            val activity = requireContext() as MainActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, VaccineNotificationFragment()).addToBackStack(null).commit()
        }

        return binding.root
    }
}