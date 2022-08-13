package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {



        val db = MainDatabase.getDatabase(application.applicationContext)



        private val pRepository = PostRepository(db.postDao,SecureFileHandle(application.applicationContext,  AuthTokenSecureFile()).file.userId)

        val posts = pRepository.readAllPosts
        val postsFromUser = pRepository.readPostsFromUser


    fun addPost(post: Post){
        viewModelScope.launch(Dispatchers.IO){
            pRepository.addPost(post)
        }
    }

//    fun readAllPostsFromUser(userId : Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            pRepository.readAllPostsFromUser(userId)
//        }
//    }


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