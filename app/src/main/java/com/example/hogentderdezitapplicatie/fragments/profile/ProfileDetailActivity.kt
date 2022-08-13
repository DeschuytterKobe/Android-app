package com.example.hogentderdezitapplicatie.fragments.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var button : Button
    private lateinit var imageView: ImageView

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        button = findViewById(R.id.btn_pick_img)
        imageView = findViewById(R.id.img_save)

        button.setOnClickListener{
            pickImageGallery()
        }
        val securefile = SecureFileHandle(applicationContext,  AuthTokenSecureFile())
        securefile.file.userId;

//        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK)
            imageView.setImageURI(data?.data)
    }



    override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.myNavHostFragment)
    return navController.navigateUp() || super.onSupportNavigateUp()
}

}