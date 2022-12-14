package com.example.petkeeper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petkeeper.R
import com.example.petkeeper.databinding.ActivityMainBinding
import com.example.petkeeper.ui.fragments.menu.AccountFragment
import com.example.petkeeper.ui.fragments.menu.MainPetListFragment
import com.example.petkeeper.ui.fragments.menu.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(MainPetListFragment())

        binding.navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_fragment -> {
                    replaceFragment(MainPetListFragment())
                }
                R.id.settings_fragment -> {
                    replaceFragment(SettingsFragment())
                }
                R.id.menu_account_fragment -> {
                    replaceFragment(AccountFragment())
                }
                else -> {
                    MainPetListFragment()
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManger = supportFragmentManager
        val fragmentTransaction = fragmentManger.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}