package com.example.petkeeper.ui.fragments.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentDewormingNotificationLayoutBinding
import com.example.petkeeper.tools.notifications.workmanager.NotificationHelper

class DewormingNotificationFragment : Fragment() {

    private lateinit var binding: FragmentDewormingNotificationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_vaccine_notification_layout,
            container,
            false
        )

        with(binding) {
            lifecycleOwner = this@DewormingNotificationFragment
            executePendingBindings()
        }

        return binding.root
    }
}