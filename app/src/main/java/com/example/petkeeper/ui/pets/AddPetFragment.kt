package com.example.petkeeper.ui.pets

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.databinding.FragmentAddPetBinding
import com.example.petkeeper.tools.AddFragmentListener
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

private const val REQUEST_CODE = 46

class AddPetFragment(private var addFragmentListener: AddFragmentListener) : Fragment() {

    private lateinit var binding: FragmentAddPetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calendar = Calendar.getInstance()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pet, container, false)

        with(binding) {
            lifecycleOwner = this@AddPetFragment
            executePendingBindings()
        }

        //Save button action
        binding.btnSave.setOnClickListener {
            val petName = binding.etPetName.text.toString()
            val petSpecies = binding.etPetSpecies.text.toString()
            val petDateOfBirth = binding.tvDob.text.toString()

            if (petName.isEmpty() || petSpecies.isEmpty() || petDateOfBirth.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val pet = Pet(petName, petSpecies, petDateOfBirth)
            addFragmentListener.onAddButtonClicked(pet)
            navigateToBackStack()
        }

        //Date picker within the dialog
        binding.btnDatepicker.setOnClickListener {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd.MM.yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.UK)
                    binding.tvDob.text = sdf.format(calendar.time)
                }
            DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        //Open camera for image capture
        binding.btnLaunchCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photo =  
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(intent, REQUEST_CODE)
            } else {
                Toast.makeText(context, "Unable to open camera!", Toast.LENGTH_SHORT).show()
            }
        }

        //Cancel button within the fragment
        binding.btnCancel.setOnClickListener {
            navigateToBackStack()
        }

        //Method to transform bitmap to String
        fun encodeImage(bm: Bitmap): String? {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        }
        return binding.root
    }

    //Image result after taken picture with camera
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            binding.petImage.setImageBitmap(takenImage)
            binding.petImage.visibility = View.VISIBLE
        } else {
            super.onActivityResult(requestCode, resultCode, data)
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