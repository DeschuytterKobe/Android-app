package com.example.hogentderdezitapplicatie

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.lifecycleScope

import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.fragments.users.list.ListAdapter
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.ReactionViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel
    private lateinit var postViewModel: PostViewModel
    private lateinit var reactionViewModel: ReactionViewModel
//    private val postAdapter by lazy { PostListAdapter(userViewModel) }
    private val adapter by lazy { ListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_post_list)

    Log.e("test waar ik inkom","in nav")

//        setupActionBarWithNavController(findNavController(R.id.myNavHostFragment))

        lifecycleScope.launch{

            //val user = User(0,"kobe","deschuytter",2,getBitmap())
           // userViewModel.addUser(user)
        }
        userViewModel.users.observe(this,{
            adapter.setData(it)
        })

        lifecycleScope.launch{
            val post = Post(0,1,"testTitel","testDescription","www.google.be", Date(),1,getBitmap(),false,false)
            postViewModel.addPost(post)
            val post2 = Post(0,2,"tweede","tewwedededed","www.google.be", Date(),1,getBitmap(),false,false)
            postViewModel.addPost(post2)
        }
//        postViewModel.posts.observe(this,{
//            postAdapter.setData(it)
//        })
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.myNavHostFragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(this)
        val request = ImageRequest.Builder(this)
            .data("https://avatars3.githubusercontent.com/u/14994036?s=400&u=2832879700f03d4b37ae1c09645352a352b9d2d0&v=4")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}