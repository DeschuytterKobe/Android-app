package com.example.hogentderdezitapplicatie

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope

import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.hogentderdezitapplicatie.fragments.users.list.ListAdapter
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    private val adapter by lazy { ListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))

        lifecycleScope.launch{
            val user = User(0,"kobe","deschuytter",2,getBitmap())
            userViewModel.addUser(user)
        }
        userViewModel.users.observe(this,{
            adapter.setData(it)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}