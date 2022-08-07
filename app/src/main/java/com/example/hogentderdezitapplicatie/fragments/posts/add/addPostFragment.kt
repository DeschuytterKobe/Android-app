package com.example.hogentderdezitapplicatie.fragments.posts.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_post.*
import kotlinx.android.synthetic.main.fragment_add_post.view.*


class addPostFragment : Fragment() {

    private lateinit var mPostViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        view.addPost_btn.setOnClickListener{
            insertPostDataToDatabase()
        }

        return view
    }

    private fun insertPostDataToDatabase() {
        val title = add_postTitle.text.toString()
        val description = add_postDescription.text.toString()
        val link = add_postLink.text.toString()


        if(inputCheck(title, description)){
            // Create User Object
            val post = Post(0 ,title, description,link)
            // Add Data to Database
            mPostViewModel.addPost(post)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addPostFragment_to_postListFragment2)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

}