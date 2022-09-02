package com.example.petkeeper.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.databinding.DialogAddPetBinding
import com.example.petkeeper.tools.AddDialogListener
import java.text.SimpleDateFormat
import java.util.*

class AddPetDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calendar = Calendar.getInstance()

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_add_pet,
            null,
            false
        )
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {

            val petName = binding.etPetName.text.toString()
            val petSpecies = binding.etPetSpecies.text.toString()
            val petDateOfBirth = binding.tvDob.text.toString()

            if (petName.isEmpty() || petSpecies.isEmpty() || petDateOfBirth.isEmpty()) {
                Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pet = Pet(petName, petSpecies, petDateOfBirth)
            addDialogListener.onAddButtonClicked(pet)
            dismiss()
        }

        binding.btnDatepicker.setOnClickListener {

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd.MM.yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.UK)
                    binding.tvDob.text = sdf.format(calendar.time)
                }

            DatePickerDialog(
                context,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnCancel.setOnClickListener {
            cancel()
        }
    }
}