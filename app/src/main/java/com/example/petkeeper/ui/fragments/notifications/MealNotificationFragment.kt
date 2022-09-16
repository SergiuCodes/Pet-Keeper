package com.example.petkeeper.ui.fragments.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentMealNotificationLayoutBinding
import com.example.petkeeper.tools.notifications.AlarmService
import java.util.*

class MealNotificationFragment : Fragment() {

    lateinit var alarmManager: AlarmService
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
        alarmManager = AlarmService(requireContext())

        with(binding) {
            lifecycleOwner = this@MealNotificationFragment
            executePendingBindings()
        }

        binding.btnDatepicker.setOnClickListener{
            setAlarm { timeInMillis -> alarmManager.setExactAlarm(timeInMillis)}
        }

        return binding.root
    }

    private fun setAlarm(callback: (Long) -> Unit) {

        Calendar.getInstance().apply {
            DatePickerDialog(
                requireContext(),
                0,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)

                    TimePickerDialog(
                        requireContext(),
                        0,
                        TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                            this.set(Calendar.MINUTE, minute)
                            this.set(Calendar.HOUR_OF_DAY, hour)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        true,
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
    }

    private fun navigateToBackStack() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .remove(this)
            .commit()
        requireActivity().supportFragmentManager.popBackStack()
    }
}















