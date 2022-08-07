package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.PostDatabase
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val readAllPostData: LiveData<List<Post>>
    private val pRepository : PostRepository

    init {
        val postDao = PostDatabase.getDatabase(application).postDao()
        pRepository = PostRepository(postDao)
        readAllPostData = pRepository.readAllPosts
    }

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
}