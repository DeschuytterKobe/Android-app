package com.example.hogentderdezitapplicatie.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hogentderdezitapplicatie.R

import android.view.View

import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle


class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val securefile = SecureFileHandle(applicationContext,  AuthTokenSecureFile())
        val myAwesomeTextView = findViewById<View>(R.id.txt_name_user) as TextView
        myAwesomeTextView.setText(securefile.file.userId.toString())

        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}