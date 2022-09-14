package com.example.petkeeper.ui.fragments.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentMealNotificationLayoutBinding

class MealNotificationFragment : Fragment() {

    private lateinit var binding: FragmentMealNotificationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_meal_notification_layout,
            container,
            false
        )

        with(binding){
            lifecycleOwner = this@MealNotificationFragment
            executePendingBindings()
        }

        return binding.root
    }
}