package com.example.hogentderdezitapplicatie.fragments.posts.open

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.fragments.posts.update.PostUpdateFragmentArgs
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_open.view.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*
import kotlinx.android.synthetic.main.fragment_post_update.view.update_postLink


class PostOpenFragment : Fragment() {

//    private val args by navArgs<PostOpenFragmentArgs>()
    private lateinit var mPostViewModel : PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_open, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
//
//        view.open_postTitle.setText(args.currentPost.title)
//        view.update_postDescription.setText(args.currentPost.description)
//        view.update_postLink.setText(args.currentPost.link)

        return view
    }



}