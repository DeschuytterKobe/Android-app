package com.example.hogentderdezitapplicatie.activies

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.hogentderdezitapplicatie.R

import android.view.View

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.fragments.users.list.ListAdapter
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.ReactionViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.*


class PostActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    private lateinit var postViewModel: PostViewModel
    private lateinit var reactionViewModel: ReactionViewModel
        private val postAdapter by lazy { PostListAdapter(userViewModel) }
    private val adapter by lazy { ListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)


        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))

        lifecycleScope.launch{

            val user = User(1,"kobe","deschuytter",2,"307f19ce-58f1-4e66-b037-fad14ec7fe68")
             userViewModel.addUser(user)
            val user2 = User(2,"frank","deschuytter",2,"307f19ce-58f1-4e66-b037-fad14ec7fe68")
            userViewModel.addUser(user2)
            val user3 = User(3,"admin","deschuytter",2,"307f19ce-58f1-4e66-b037-fad14ec7fe68")
            userViewModel.addUser(user3)
        }
        userViewModel.users.observe(this,{
            adapter.setData(it)
        })

//        lifecycleScope.launch{
//            val post = Post(0,1,"testTitel","testDescription","www.google.be", Date(),1,getBitmap(),false,false)
//            postViewModel.addPost(post)
//            val post2 = Post(0,2,"tweede","tewwedededed","www.google.be", Date(),1,getBitmap(),false,false)
//            postViewModel.addPost(post2)
//        }
//        postViewModel.readAllPosts().observe(this,{
//            postAdapter.setData(it)
//        })
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    @Override
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.clear() // We don't want a TransactionTooLargeException, so we handle things via the SavedInstanceFragment
    }
}