package com.example.petkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.petkeeper.ui.PetListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container, PetListFragment()).commit()
    }
}