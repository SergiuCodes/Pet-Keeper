package com.example.petkeeper.ui.fragments.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.FragmentAccountLayoutBinding
import com.example.petkeeper.tools.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AccountFragment : Fragment() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val user: FirebaseUser? = auth.currentUser

    private lateinit var binding: FragmentAccountLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_account_layout,
            container,
            false
        )
        with(binding) {
            lifecycleOwner = this@AccountFragment
            executePendingBindings()
        }
        //Check if user is signed in with Google
        checkIfSignedIn()

        //Call the login button method
        initGoogleLogin()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constants.REQUEST_CODE_SIGN_IN) {
            if (user != null) {
                //User is signed in
                binding.btnLoginGoogle.visibility = View.GONE
                binding.tvLoginStatus.text = getString(R.string.logged_in) + user.email
                binding.tvLoginStatus.visibility = View.VISIBLE

            } else {
                //User is not signed in
                binding.btnLoginGoogle.visibility = View.VISIBLE
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                account?.let {
                    googleAuthForFirebase(it)
                    binding.btnLoginGoogle.visibility = View.GONE
                    binding.tvLoginStatus.text = getString(R.string.logged_in) + " " + "\n" + account.email.toString()
                    binding.tvLoginStatus.visibility = View.VISIBLE
                }
            }
        }
    }

    fun initGoogleLogin() {
        binding.btnLoginGoogle.setOnClickListener {
            val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.webclient_id))
                .requestEmail()
                .build()
            val signInClient = GoogleSignIn.getClient(requireContext(), options)
            signInClient.signInIntent.also {
                startActivityForResult(it, Constants.REQUEST_CODE_SIGN_IN)
            }
        }
    }

    private fun googleAuthForFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.id, null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Successfully logged in!", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkIfSignedIn(){

        if (user != null) {
            //User is signed in
            binding.btnLoginGoogle.visibility = View.GONE
            binding.tvLoginStatus.text = getString(R.string.logged_in) + user.email.toString()
            Log.d("AccountFragment","testing signed user name " + binding.tvLoginStatus.toString())
            binding.tvLoginStatus.visibility = View.VISIBLE
        } else {
            binding.btnLoginGoogle.visibility = View.VISIBLE
            binding.tvLoginStatus.visibility = View.GONE
        }
    }
}