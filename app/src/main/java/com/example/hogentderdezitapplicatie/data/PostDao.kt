package com.example.hogentderdezitapplicatie.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPost(post: Post)

    @Query("SELECT * FROM post_table ORDER BY id ASC")
    fun readAllPosts(): LiveData<List<Post>>

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)
}