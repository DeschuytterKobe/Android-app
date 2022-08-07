package com.example.hogentderdezitapplicatie.fragments.posts.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_list.view.*

class PostListFragment : Fragment() {

    private lateinit var mPostViewModel : PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        val adapter = PostListAdapter()
        val recyclerView = view.postRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mPostViewModel.readAllPostData.observe(viewLifecycleOwner, Observer { post ->
            adapter.setData(post)
        })

        view.floatingActionButtonForPosts.setOnClickListener{
            findNavController().navigate(R.id.action_postListFragment2_to_addPostFragment)
        }

        return view

    }


}