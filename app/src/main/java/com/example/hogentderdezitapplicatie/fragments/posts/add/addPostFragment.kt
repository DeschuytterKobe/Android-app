package com.example.hogentderdezitapplicatie.fragments.posts.add


import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.converters.Converters
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_add_post.*
import kotlinx.android.synthetic.main.fragment_add_post.view.*
import kotlinx.coroutines.launch
import java.util.*


class addPostFragment : Fragment() {

    private lateinit var mPostViewModel: PostViewModel

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var converters: Converters

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add_post, container, false)

        button = view.findViewById(R.id.add_picture_button)

        imageView = view.findViewById(R.id.add_image_to_post)

        button.setOnClickListener {
            pickImageGallery()
        }

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        view.addPost_btn.setOnClickListener {
            insertPostDataToDatabase()
        }


        return view
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, addPostFragment.IMAGE_REQUEST_CODE)
    }

    private fun insertPostDataToDatabase() {


        var userId = arguments?.getInt("userId")
        var bundle = Bundle()
        if (userId != null) {
            bundle.putInt("userId", userId)
        }

        val description = add_postDescription.text.toString()
        val link = add_postLink.text.toString()

        val bytes = (imageView.drawable as BitmapDrawable).bitmap

        if (inputCheck("title", description)) {
            // Create User Object
            lifecycleScope.launch {
                //vervang bytes door de url
                val post =
                    Post(0, userId!!, "title", description, link, Date(), 0, bytes, false, false)
                mPostViewModel.addPost(post)
            }


            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addPostFragment_to_postListFragment2, bundle)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addPostFragment.IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK)
            imageView.setImageURI(data?.data)
    }


}