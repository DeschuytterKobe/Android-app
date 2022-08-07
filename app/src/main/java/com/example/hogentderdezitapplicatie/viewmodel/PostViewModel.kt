package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.PostDatabase
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Post>>
    private val repository : PostRepository

    init {
        val postDao = PostDatabase.getDatabase(application).postDao()
        repository = PostRepository(postDao)
        readAllData = repository.readAllPosts
    }

    fun addPost(post: Post){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPost(post)
        }
    }
}