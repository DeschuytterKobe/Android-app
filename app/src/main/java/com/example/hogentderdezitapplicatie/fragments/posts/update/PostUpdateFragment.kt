package com.example.hogentderdezitapplicatie.fragments.posts.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.fragments.users.update.UpdateFragmentArgs
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_update.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*


class PostUpdateFragment : Fragment() {

    private val args by navArgs<PostUpdateFragmentArgs>()
    private lateinit var mPostViewModel : PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_post_update, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        view.update_postTitle.setText(args.currentPost.title)
        view.update_postDescription.setText(args.currentPost.description)
        view.update_postLink.setText(args.currentPost.link)

        view.updatePost_btn.setOnClickListener{
            updatePost()
        }

        return view
    }

    private fun updatePost(){
        val title = update_postTitle.text.toString()
        val description = update_postDescription.text.toString()
        val link = update_postLink.text.toString()

        if(inputCheck(title,description)){
            val updatePost = Post(args.currentPost.id,title,description,link)

            mPostViewModel.updatePost(updatePost)
            Toast.makeText(requireContext(),"Updated Succesfully!",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_postUpdateFragment_to_postListFragment2)
        }else
        {
            Toast.makeText(requireContext(),"fill in everything",Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}