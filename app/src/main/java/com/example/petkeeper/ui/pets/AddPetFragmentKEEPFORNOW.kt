//package com.example.petkeeper.ui.pets
//
//import android.app.DatePickerDialog
//import android.content.Intent
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.FileProvider
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import com.example.petkeeper.BuildConfig
//import com.example.petkeeper.R
//import com.example.petkeeper.data.database.room.entity.Pet
//import com.example.petkeeper.databinding.FragmentAddPetBinding
//import com.example.petkeeper.tools.AddFragmentListener
//import java.io.File
//import java.text.SimpleDateFormat
//import java.util.*
//
//private const val REQUEST_CODE = 46
//
//class AddPetFragmentKEEPFORNOW(private var addFragmentListener: AddFragmentListener) : Fragment() {
//
//    val APP_TAG = "PetKeeperApp"
//    val photoFileName = "photo.jpg"
//    var photoFile: File? = null
//    var cameraResultLauncher: ActivityResultLauncher<Intent>? = null
//
//    private lateinit var binding: FragmentAddPetBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val calendar = Calendar.getInstance()
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_pet, container, false)
//
//        with(binding) {
//            lifecycleOwner = this@AddPetFragmentKEEPFORNOW
//            executePendingBindings()
//        }
//
//        cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//                result: ActivityResult ->
//            if(result.resultCode == REQUEST_CODE){
//                val takenImage = BitmapFactory.decodeFile(photoFile!!.absolutePath)
//                binding.petImage.setImageBitmap(takenImage)
//                binding.petImage.visibility = View.VISIBLE
//            }else{
//                Toast.makeText(requireContext(),"Error taking picture", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        //Save button action
//        binding.btnSave.setOnClickListener {
//            val petName = binding.etPetName.text.toString()
//            val petSpecies = binding.etPetSpecies.text.toString()
//            val petDateOfBirth = binding.tvDob.text.toString()
//
//            if (petName.isEmpty() || petSpecies.isEmpty() || petDateOfBirth.isEmpty()) {
//                Toast.makeText(requireContext(), "All fields are required!", Toast.LENGTH_SHORT)
//                    .show()
//                return@setOnClickListener
//            }
//            val pet = Pet(petName, petSpecies, petDateOfBirth)
//            addFragmentListener.onAddButtonClicked(pet)
//            navigateToBackStack()
//        }
//
//        //Date picker within the dialog
//        binding.btnDatepicker.setOnClickListener {
//            val dateSetListener =
//                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
//                    calendar.set(Calendar.YEAR, year)
//                    calendar.set(Calendar.MONTH, monthOfYear)
//                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//                    val myFormat = "dd.MM.yyyy"
//                    val sdf = SimpleDateFormat(myFormat, Locale.UK)
//                    binding.tvDob.text = sdf.format(calendar.time)
//                }
//            DatePickerDialog(
//                requireContext(),
//                dateSetListener,
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }
//
//        //Open camera for image capture
//        binding.btnLaunchCamera.setOnClickListener {
//            launchCamera()
//        }
//
//        //Cancel button within the fragment
//        binding.btnCancel.setOnClickListener {
//            navigateToBackStack()
//        }
//        return binding.root
//    }
//
//    //EXTRA
//    //Method to back action to previous fragment
//    private fun navigateToBackStack() {
//        requireActivity().supportFragmentManager
//            .beginTransaction()
//            .remove(this)
//            .commit()
//        requireActivity().supportFragmentManager.popBackStack()
//    }
//
//    //Method to launch Camera and take picture
//    private fun launchCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        photoFile = getPhotoFileUri(photoFileName)
//
//        if (photoFile != null) {
//            val provider: Uri = FileProvider.getUriForFile(
//                Objects.requireNonNull(requireContext()),
//                BuildConfig.APPLICATION_ID+ ".provider",
//                photoFile!!
//            )
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, provider)
//            if (intent.resolveActivity(requireActivity().packageManager) != null) {
//                startActivityForResult(intent, REQUEST_CODE)
//            }
//        }
//    }
//
//    //Return the File for a photo stored on disck given the filename
//    private fun getPhotoFileUri(fileName: String): File {
//        val mediaStorageDir = File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG)
//        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
//            Log.d(APP_TAG,"failed to create directory!")
//        }
//        return File(mediaStorageDir.path + File.separator + fileName)
//    }
//}