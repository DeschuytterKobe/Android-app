package com.example.hogentderdezitapplicatie


import android.graphics.Bitmap
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.data.ReactionDao
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class PostTest {
    private lateinit var postDao: PostDao
    private lateinit var reactionDao: ReactionDao
    private lateinit var db: MainDatabase

//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        postDao = db.postDao
        reactionDao = db.reactionDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    fun addPost() {
        var bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            .copy(Bitmap.Config.ARGB_8888, true)
        val createPost = Post(1, 1, "", "", "", Date(), 1, bitmap, false, false)
        postDao.addPost(createPost)
    }

    @Test
    @Throws(Exception::class)
    fun addMultiplePostToList() {
        addPost()
        var bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            .copy(Bitmap.Config.ARGB_8888, true)
        val createPost2 = Post(2, 1, "", "", "", Date(), 1, bitmap, false, false)
        postDao.addPost(createPost2)
        assertEquals(2, postDao.getCount())
    }

    @Test
    @Throws(Exception::class)
    fun addPostToList() {
        addPost()
        postDao.readPostWithId(1).value?.let { assertEquals(it.id, 1) }
    }

    @Test
    fun updatePost() {
        var bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            .copy(Bitmap.Config.ARGB_8888, true)
        addPost()
        val post2 = Post(1, 2, "", "", "", Date(), 1, bitmap, false, false)
        postDao.updatePost(post2)
        postDao.readPostWithId(1).value?.let { assertEquals(it.userId, 2) }

    }

    @Test
    fun deletePost() {

        var bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
            .copy(Bitmap.Config.ARGB_8888, true)
        val createPost = Post(1, 1, "", "", "", Date(), 1, bitmap, false, false)
        postDao.addPost(createPost)
        postDao.deletePost(createPost)
        assertEquals(0, postDao.getCount())

    }

    fun addReaction() {

        val createReaction = Reaction(1, 1, 1, "", Date())
        reactionDao.addReaction(createReaction)
    }

    @Test
    @Throws(Exception::class)
    fun addMultipleReactions() {
        addReaction()
        val createReaction2 = Reaction(2, 1, 1, "", Date())
        reactionDao.addReaction(createReaction2)
        assertEquals(2, reactionDao.getCount())
    }

    @Test
    @Throws(Exception::class)
    fun reactionAddedToRightPost() {
        addReaction()
        reactionDao.getReactionsFromPost(1).value?.let { assertEquals(it[1].postId, 1) }
    }

    @Test
    @Throws(Exception::class)
    fun reactionAdded() {
        addReaction()
        assertEquals(1, reactionDao.getCount())
    }

    @Test
    fun updateReaction() {

        addReaction()
        val reaction2 = Reaction(1, 1, 1, "test", Date())
        reactionDao.updateReaction(reaction2)
        reactionDao.getReactionWithId(1).value?.let { assertEquals(it.content, "test") }

    }

    @Test
    fun deleteReaction() {

        val createReaction = Reaction(1, 1, 1, "test", Date())
        reactionDao.deleteReaction(createReaction)
        assertEquals(0, reactionDao.getCount())

    }


}