package com.example.hogentderdezitapplicatie.fragments.posts.update

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
import androidx.navigation.findNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.fragments.posts.add.addPostFragment
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_update.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*
import kotlinx.coroutines.launch
import java.util.*


class PostUpdateFragment : Fragment() {

    //    private val args by navArgs<PostUpdateFragmentArgs>()
    private lateinit var mPostViewModel: PostViewModel
    private var postRead = false
    private var postAnswered = false
    private var postLiked = 0
    private lateinit var button: Button
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var userId = SecureFileHandle(context, AuthTokenSecureFile()).file.userId
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_update, container, false)

        button = view.findViewById(R.id.edit_picture_button)
        imageView = view.findViewById(R.id.update_post_image)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        button.setOnClickListener {
            pickImageGallery()
        }

        var postId = arguments?.getInt("postId")

        mPostViewModel.readPostWithId(postId).observe(viewLifecycleOwner) {
            view.update_postDescription.setText(it.description)
            view.update_postLink.setText(it.link)
            view.update_post_image.setImageBitmap(it.picture)
            postRead = it.read
            postAnswered = it.answered
            postLiked = it.liked
        }



        view.updatePost_btn.setOnClickListener {
            updatePost()
            var bundle = Bundle()
            bundle.putInt("userId", userId)
            it.findNavController()
                .navigate(R.id.action_postUpdateFragment_to_postListFragment2, bundle)
        }


        return view
    }

    private fun updatePost() {
        val description = update_postDescription.text.toString()
        val link = update_postLink.text.toString()
        val image = update_post_image
        val bytes = (image.drawable as BitmapDrawable).bitmap

        if (inputCheck("title", description)) {
            lifecycleScope.launch {
                val updatePost = arguments?.getInt("postId")?.let {
                    Post(
                        it,
                        SecureFileHandle(context, AuthTokenSecureFile()).file.userId,
                        "",
                        description,
                        link,
                        Date(),
                        postLiked,
                        bytes,
                        postRead,
                        postAnswered
                    )
                }
                if (updatePost != null) {
                    mPostViewModel.updatePost(updatePost)
                }
            }




            Toast.makeText(requireContext(), "Updated Succesfully!", Toast.LENGTH_SHORT).show()


        } else {
            Toast.makeText(requireContext(), "fill in everything", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, addPostFragment.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addPostFragment.IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK)
            imageView.setImageURI(data?.data)
    }

    private fun inputCheck(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}