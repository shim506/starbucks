package com.example.starbucks.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.starbucks.R
import com.example.starbucks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            findNavController(R.id.nav_host)
        )
        binding.bottomNavigation.setupWithNavController(findNavController(R.id.nav_host))
    }
}