package com.example.petkeeper.tools.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*

class DateAndTimePickerHelper() {

    companion object {
        fun getDateAndTime(context: Context){

            var yearPicked = 0
            var monthPicked = 0
            var dayPicked = 0
            var hourPicked  = 0
            var minutePicked = 0

            Calendar.getInstance().apply {
                DatePickerDialog(
                    context,
                    0,
                    { _, year, month, day ->
                        this.set(Calendar.YEAR, year)
                        this.set(Calendar.MONTH, month)
                        this.set(Calendar.DAY_OF_MONTH, day)

                        yearPicked = year
                        monthPicked = month
                        dayPicked = day

                        TimePickerDialog(
                            context,
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
}