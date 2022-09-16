package com.example.petkeeper.ui.fragments.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentVaccineNotificationLayoutBinding
import com.example.petkeeper.tools.Constants
import com.example.petkeeper.tools.notifications.workmanager.WorkerClass
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class VaccineNotificationFragment : Fragment() {

    private lateinit var binding: FragmentVaccineNotificationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var yearPicked = 0
        var monthPicked = 0
        var dayPicked = 0
        var hourPicked  = 0
        var minutePicked = 0

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_vaccine_notification_layout,
            container,
            false
        )
        with(binding) {
            lifecycleOwner = this@VaccineNotificationFragment
            executePendingBindings()
        }

        binding.btnScheduleNotification.setOnClickListener{

            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK)

            val calendar = Calendar.getInstance()
            calendar.set(yearPicked, monthPicked, dayPicked, hourPicked, minutePicked)

            val today = Calendar.getInstance()
            val differenceInTime = (calendar.timeInMillis/1000L) - (today.timeInMillis/1000L)

            val petName = binding.etPetName.text.toString()
            val notificationTitle = binding.etNotificationTitle.text.toString()

            val myWorkRequest = OneTimeWorkRequestBuilder<WorkerClass>()
                .setInitialDelay(differenceInTime, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        Constants.titleExtra to petName,
                        Constants.messageExtra to notificationTitle
                    )
                )
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

            Toast.makeText(requireContext(), "Notification scheduled for $petName on " + sdf.format(calendar.timeInMillis), Toast.LENGTH_LONG).show()
        }

        binding.btnDatepicker.setOnClickListener {

                Calendar.getInstance().apply {
                    DatePickerDialog(
                        requireContext(),
                        0,
                        { _, year, month, day ->
                            this.set(Calendar.YEAR, year)
                            this.set(Calendar.MONTH, month)
                            this.set(Calendar.DAY_OF_MONTH, day)

                            yearPicked = year
                            monthPicked = month
                            dayPicked = day

                            TimePickerDialog(
                                requireContext(),
                                0,
                                { _, hour, minute ->
                                    this.set(Calendar.MINUTE, minute)
                                    this.set(Calendar.HOUR_OF_DAY, hour)

                                    hourPicked = hour
                                    minutePicked = minute
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
        return binding.root
    }
}