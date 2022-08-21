package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {


    val db = MainDatabase.getDatabase(application.applicationContext)


    private val pRepository = PostRepository(
        db.postDao,
        SecureFileHandle(application.applicationContext, AuthTokenSecureFile()).file.userId
    )

//        val posts : LiveData<List<Post>> =  pRepository.readAllPosts
//        val postsFromUser = pRepository.readPostsFromUser


    fun readAllPosts(): LiveData<List<Post>> {

        return pRepository.readAllPosts()

    }

    fun readAllPostsFromUser(userId: Int): LiveData<List<Post>> {

        return pRepository.readAllPostsFromUser(userId)
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.addPost(post)
        }
    }

    fun readAllPostsFromUserAndFavorite(userId: Int): LiveData<List<Post>> {
        return pRepository.readAllPostsFromUserAndFavorite(userId)
    }


    fun readPostWithId(postId: Int?): LiveData<Post> {

        return pRepository.readPostWithId(postId)


    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.updatePost(post)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.deletePost(post)
        }
    }

}


public class PostOpenViewModel(application: Application) : AndroidViewModel(application) {

    val db = MainDatabase.getDatabase(application.applicationContext)


    private val pRepository = PostRepository(
        db.postDao,
        SecureFileHandle(application.applicationContext, AuthTokenSecureFile()).file.userId
    )


    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.addPost(post)
        }
    }

//    fun readAllPostsFromUser(userId : Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            pRepository.readAllPostsFromUser(userId)
//        }
//    }


    fun readPostWithId(postId: Int?): LiveData<Post> {

        return pRepository.readPostWithId(postId)


    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.updatePost(post)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            pRepository.deletePost(post)
        }
    }
}