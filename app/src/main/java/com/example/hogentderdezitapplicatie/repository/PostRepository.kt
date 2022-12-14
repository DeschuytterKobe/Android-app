package com.example.hogentderdezitapplicatie.repository

import androidx.lifecycle.LiveData
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.model.Post

class PostRepository(private val postDao: PostDao, userId: Int) {


//    val readAllPosts : LiveData<List<Post>> = postDao.readAllPosts()
//    val readPostsFromUser :  LiveData<List<Post>> = postDao.readAllPostsFromUser(userId)

    suspend fun addPost(post: Post) {
        postDao.addPost(post)
    }

    fun readAllPostsFromUser(userId: Int): LiveData<List<Post>> {
        return postDao.readAllPostsFromUser(userId)
    }

    fun readPostWithId(postId: Int?): LiveData<Post> {
        return postDao.readPostWithId(postId)
    }

    fun readAllPostsFromUserAndFavorite(userId: Int): LiveData<List<Post>> {
        return postDao.readAllPostsFromUserAndFavorite(userId)
    }


    suspend fun updatePost(post: Post) {
        postDao.updatePost(post)
    }

    suspend fun deletePost(post: Post) {
        postDao.deletePost(post)
    }

    fun readAllPosts(): LiveData<List<Post>> {
        return postDao.readAllPosts()
    }
}