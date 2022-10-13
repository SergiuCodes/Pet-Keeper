package com.example.petkeeper.ui.fragments.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.database.room.entity.Notification
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.repository.NotificationsRepository
import com.example.petkeeper.databinding.FragmentAddNotificationLayoutBinding
import com.example.petkeeper.tools.Constants
import com.example.petkeeper.tools.notifications.workmanager.WorkerClass
import com.example.petkeeper.viewmodel.notifications.NotificationsViewModel
import com.example.petkeeper.viewmodel.notifications.NotificationsViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AddNotificationFragment : Fragment() {

    var yearPicked = 0
    var monthPicked = 0
    var dayPicked = 0
    var hourPicked  = 0
    var minutePicked = 0

    private lateinit var binding: FragmentAddNotificationLayoutBinding
    private lateinit var database: PetRoomDatabase
    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_notification_layout,
            container,
            false
        )

        database = PetRoomDatabase(requireContext())
        val repository = NotificationsRepository(database)
        val factory = NotificationsViewModelFactory(repository)
        notificationsViewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]

        with(binding) {
            lifecycleOwner = this@AddNotificationFragment
            executePendingBindings()
        }

        //Call the scheduler
        openScheduler()
        //Confirm notification scheduled
        scheduleNotification()

        return binding.root
    }

    //Open the scheduler
    private fun openScheduler() {
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
    }

    //Confirm the scheduled date and go back
    private fun scheduleNotification() {
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
            notificationsViewModel.insertNotification(Notification(petName,sdf.format(calendar.timeInMillis),1))
            navigateToBackStack()
        }
    }

    //Method to back action to previous fragment
    private fun navigateToBackStack() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .remove(this)
            .commit()
        requireActivity().supportFragmentManager.popBackStack()
    }
}