package com.example.hogentderdezitapplicatie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

//        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.myNavHostFragment)
//       return navController.navigateUp() || super.onSupportNavigateUp()
      }
}