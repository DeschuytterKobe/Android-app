package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {



        val db = MainDatabase.getDatabase(application.applicationContext)



        private val pRepository = PostRepository(db.postDao)

        val posts = pRepository.readAllPosts


    fun addPost(post: Post){
        viewModelScope.launch(Dispatchers.IO){
            pRepository.addPost(post)
        }
    }

    fun updatePost(post: Post){
        viewModelScope.launch(Dispatchers.IO){
            pRepository.updatePost(post)
        }
    }

    fun deletePost(post: Post){
        viewModelScope.launch(Dispatchers.IO){
            pRepository.deletePost(post)
        }
    }

}