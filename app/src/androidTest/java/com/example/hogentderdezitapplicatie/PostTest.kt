package com.example.hogentderdezitapplicatie



import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry


import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

import java.io.IOException

import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.model.Post
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostTest {
    private lateinit var postDao: PostDao
    private lateinit var db: MainDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.g
        db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        postDao = db.postDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
//        val post = Post(1,1,"","","", Date(),0,false,false)
//        postDao.addPost(post)


        val post : LiveData<Post> = postDao.readPostWithId(1)
//        val currentItem = post[1]
        assertEquals(post.value,1)
    }


//    private suspend fun getBitmap(): Bitmap {
//        val loading = ImageLoader(this)
//        val request = ImageRequest.Builder()
//            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
//            .build()
//
//        val result = (loading.execute(request) as SuccessResult).drawable
//        return (result as BitmapDrawable).bitmap
//    }
}