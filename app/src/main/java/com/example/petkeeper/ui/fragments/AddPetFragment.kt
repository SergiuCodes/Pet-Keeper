package com.example.petkeeper.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.petkeeper.R
import com.example.petkeeper.data.database.room.entity.Pet
import com.example.petkeeper.data.database.room.PetRoomDatabase
import com.example.petkeeper.data.repository.PetsRepository
import com.example.petkeeper.databinding.FragmentAddPetBinding
import com.example.petkeeper.tools.Constants
import com.example.petkeeper.viewmodel.addpetscreen.AddPetViewModel
import com.example.petkeeper.viewmodel.addpetscreen.AddPetViewModelFactory
import java.io.FileDescriptor
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


class AddPetFragment : Fragment() {

    private var imageUri: Uri? = null
    private var petImage: Bitmap? = null
    private lateinit var database: PetRoomDatabase
    private lateinit var addPetViewModel: AddPetViewModel
    private lateinit var binding: FragmentAddPetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pet, container, false)

        database = PetRoomDatabase(requireContext())
        val repository = PetsRepository(database)
        val factory = AddPetViewModelFactory(repository)
        addPetViewModel = ViewModelProvider(this, factory)[AddPetViewModel::class.java]

        with(binding) {
            lifecycleOwner = this@AddPetFragment
            executePendingBindings()
        }
        //Method to open gallery and pick image
        openGallery()

        //Method to launch camera and pick image
        launchCamera()

        //Choose birthday method
        birthdayPickerLauncher()

        //Pet insert
        addPet()

        //Cancel action -> back to prev. frag.
        binding.btnCancel.setOnClickListener {
            navigateToBackStack()
        }
        return binding.root
    }

    //Method to open gallery
    private fun openGallery() {
        binding.btnOpenGallery.setOnClickListener {

            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = ("image/*")
            if (gallery.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(Intent.createChooser(gallery,"Pick an image"), Constants.REQUEST_CODE_GALLERY)
            } else {
                Toast.makeText(context, "Unable to open gallery!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Method to launch camera
    private fun launchCamera() {
        binding.btnLaunchCamera.setOnClickListener {

            val camera= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (camera.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(camera, Constants.REQUEST_CODE_CAMERA)
            } else {
                Toast.makeText(context, "Unable to open camera!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Image result on launching camera/gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == Constants.REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            val takenImageCamera: Bitmap? = data?.getParcelableExtra("data")
            binding.petImage.setImageBitmap(takenImageCamera)
            binding.petImage.visibility = View.VISIBLE
            petImage = takenImageCamera
        }

        if (requestCode == Constants.REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK) {

            val inputStream: InputStream? = data?.data?.let {
                requireContext().contentResolver.openInputStream(
                    it
                )
            }
            val galleryPic: Bitmap = BitmapFactory.decodeStream(inputStream)
            petImage = galleryPic
            binding.petImage.visibility = View.VISIBLE
            binding.petImage.setImageBitmap(galleryPic)

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    //Method to open and pick date
    private fun birthdayPickerLauncher() {

        val calendar = Calendar.getInstance()
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
    }

    //Method to insert pet in the db after chosen details
    private fun addPet() {
        binding.btnSave.setOnClickListener {
            val petName = binding.etPetName.text.toString()
            val petSpecies = binding.etPetSpecies.text.toString()
            val petDateOfBirth = binding.tvDob.text.toString()

            if (petName.isEmpty() || petSpecies.isEmpty() || petDateOfBirth.isEmpty() || petImage == null) {
                Toast.makeText(requireContext(), "All fields are required!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val pet = Pet(petName, petSpecies, petDateOfBirth, petImage!!)
            addPetViewModel.insertPet(pet)
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

    private fun uriToBitmap(selectedFileUri: Uri?): Bitmap? {
        try {
            val parcelFileDescriptor: ParcelFileDescriptor? =
                selectedFileUri?.let { requireContext().contentResolver.openFileDescriptor(it, "r") }
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}