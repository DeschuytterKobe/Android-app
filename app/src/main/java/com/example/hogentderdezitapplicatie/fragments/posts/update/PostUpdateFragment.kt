package com.example.hogentderdezitapplicatie.fragments.posts.update

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_update.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*
import kotlinx.coroutines.launch
import java.util.*


class PostUpdateFragment : Fragment() {

//    private val args by navArgs<PostUpdateFragmentArgs>()
    private lateinit var mPostViewModel : PostViewModel
    private lateinit var post :Post
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var userId = SecureFileHandle(context,  AuthTokenSecureFile()).file.userId
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_post_update, container, false)

        Log.d("1","In de postupdatefragment")

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)


        var postId = arguments?.getInt("postId")
        Log.d("main",postId.toString())

        mPostViewModel.readPostWithId(postId).observe(viewLifecycleOwner ){
            view.update_postTitle.setText(it.title)
             view.update_postDescription.setText(it.description)
            view.update_postLink.setText(it.link)
            view.update_post_image.setImageBitmap(it.picture)
        }
        //TODO laad van database en vull viemodel


//            view.update_postTitle.setText(queryResult.title)
//            view.update_postDescription.setText(queryResult.description)
//            view.update_postLink.setText(queryResult.link)





        view.updatePost_btn.setOnClickListener{
            updatePost()
            var bundle = Bundle()
            bundle.putInt("userId", userId)
            it.findNavController().navigate(R.id.action_postUpdateFragment_to_postListFragment2,bundle)
        }


        return view
    }

    private fun updatePost(){
        val title = update_postTitle.text.toString()
        val description = update_postDescription.text.toString()
        val link = update_postLink.text.toString()
        val image = update_post_image
        val bytes= (image.drawable as BitmapDrawable).bitmap

        if(inputCheck(title,description)){
            lifecycleScope.launch{
                val updatePost = arguments?.getInt("postId")?.let {
                    Post(
                        it,
                        SecureFileHandle(context,  AuthTokenSecureFile()).file.userId,
                        title,
                        description,
                        link,
                        Date(),
                        0,
                        bytes
                    )
                }
                if (updatePost != null) {
                    mPostViewModel.updatePost(updatePost)
                }
            }




            Toast.makeText(requireContext(),"Updated Succesfully!",Toast.LENGTH_SHORT).show()



        }else
        {
            Toast.makeText(requireContext(),"fill in everything",Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
}