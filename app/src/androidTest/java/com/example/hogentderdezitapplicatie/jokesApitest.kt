package com.example.hogentderdezitapplicatie

import android.graphics.Bitmap
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.model.Post
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class jokesApitest {

    private lateinit var postDao: PostDao
    private lateinit var db: MainDatabase

    @Before
    fun createDb() {
        Log.i("before", "running before")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
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
    fun insertAndGetJoke() = runBlocking {
        var bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            .copy(Bitmap.Config.ARGB_8888, true)
        val createPost = Post(1, 1, "", "", "", Date(), 1, bitmap, false, false)
        postDao.addPost(createPost)

        val createPost2 = Post(2, 1, "", "", "", Date(), 1, bitmap, false, false)
        postDao.addPost(createPost2)
        val postWithId = postDao.readPostWithIdForTest(1)
        assertEquals(postWithId.id, 1)
    }
}