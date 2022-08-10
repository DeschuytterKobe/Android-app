package com.example.hogentderdezitapplicatie.repository

import androidx.lifecycle.LiveData
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User

class PostRepository(private val postDao : PostDao) {

    val readAllPosts : LiveData<List<Post>> = postDao.readAllPosts()

    suspend fun addPost(post: Post){
        postDao.addPost(post)
    }

    suspend fun updatePost(post: Post){
        postDao.updatePost(post)
    }
    suspend fun deletePost(post: Post){
        postDao.deletePost(post)
    }
}