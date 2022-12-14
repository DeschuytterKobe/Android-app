package com.example.hogentderdezitapplicatie.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hogentderdezitapplicatie.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPost(post: Post)

    @Query("SELECT * FROM post_table ORDER BY postDate DESC")
    fun readAllPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM post_table  WHERE Id = :postId")
    fun readPostWithId(postId: Int?): LiveData<Post>

    @Query("SELECT * FROM post_table  WHERE Id = :postId")
    fun readPostWithIdForTest(postId: Int): Post

    @Query("SELECT * FROM post_table WHERE userId = :userId ORDER BY postDate DESC ")
    fun readAllPostsFromUser(userId: Int): LiveData<List<Post>>

    @Query("SELECT * FROM post_table WHERE userId = :userId AND liked == 1")
    fun readAllPostsFromUserAndFavorite(userId: Int): LiveData<List<Post>>

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("SELECT COUNT(id) FROM post_table")
    fun getCount(): Int
}