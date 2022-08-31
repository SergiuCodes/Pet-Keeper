package com.example.petkeeper.ui

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

class AddPetDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    private lateinit var binding:DialogAddPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_pet, null, false)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener{
            val petName = binding.etPetName.text.toString()
            val petSpecies = binding.etPetSpecies.text.toString()
            val petDateOfBirth = binding.etPetDob.text.toString()

            if(petName.isEmpty() || petSpecies.isEmpty() || petDateOfBirth.isEmpty()){
                Toast.makeText(context, "Please enter all the required info!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val pet = Pet(petName, petSpecies, petDateOfBirth)
            addDialogListener.onAddButtonClicked(pet)
            dismiss()
        }
        binding.btnCancel.setOnClickListener{
            cancel()
        }
    }
}