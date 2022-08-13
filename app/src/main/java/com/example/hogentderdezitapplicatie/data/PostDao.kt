package com.example.hogentderdezitapplicatie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.hogentderdezitapplicatie.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPost(post: Post)

    @Query("SELECT * FROM post_table ORDER BY id ASC")
    fun readAllPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM post_table  WHERE Id = :postId")
    fun readPostWithId(postId: Int?): LiveData<Post>

    @Query("SELECT * FROM post_table WHERE userId = :userId ")
    fun readAllPostsFromUser(userId : Int): LiveData<List<Post>>

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)
}