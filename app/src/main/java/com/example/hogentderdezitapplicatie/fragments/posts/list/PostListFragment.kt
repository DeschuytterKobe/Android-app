package com.example.hogentderdezitapplicatie.fragments.posts.list

import android.os.Bundle
import android.util.Log
import android.util.Log.ERROR
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.utils.SpacingItemsDecorator
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_post_list.view.*

class PostListFragment : Fragment() {

    private lateinit var mPostViewModel: PostViewModel
    private lateinit var uUserViewModel: UserViewModel
    private lateinit var spacingItemsDecorator: SpacingItemsDecorator
    private var liked : Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        try {


        // Inflate the layout for this fragment
        var userId = SecureFileHandle(context, AuthTokenSecureFile()).file.userId


        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        Log.e("DBG","in postlist fragementbase before creation userviewmodel");
        uUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        Log.e("DBG","in postlist fragementbase after creation userviewmodel");



        val adapter = PostListAdapter(uUserViewModel)
        val recyclerView = view.postRecyclerview
        spacingItemsDecorator = SpacingItemsDecorator(10)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(spacingItemsDecorator)

        Log.e("DBG","in postlist fragementbase before creation postvm");

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        Log.e("DBG","in postlist fragementbase after creation postvm");

    if(userId ==3){
        view.showLikedPostsButton.isVisible=false
    }

            view.showLikedPostsButton.setOnClickListener {
                if(liked){
                    if (userId == 3) {

                        mPostViewModel.readAllPosts().observe(viewLifecycleOwner, Observer { post ->
                            adapter.setData(post)
                        })
                    } else {

                        mPostViewModel.readAllPostsFromUser(userId)
                            .observe(viewLifecycleOwner, Observer { post ->
                                adapter.setData(post)
                            })
                    }
                    liked=false
                    view.showLikedPostsButton.setText("liked")
                }else{
                    mPostViewModel.readAllPostsFromUserAndFavorite(userId)
                        .observe(viewLifecycleOwner, Observer { post ->
                            adapter.setData(post)
                        })
                    liked = true
                    view.showLikedPostsButton.setText("refresh")
                }



            }


                if (userId == 3) {

                    mPostViewModel.readAllPosts().observe(viewLifecycleOwner, Observer { post ->
                        adapter.setData(post)
                    })
                } else {

                    mPostViewModel.readAllPostsFromUser(userId)
                        .observe(viewLifecycleOwner, Observer { post ->
                            adapter.setData(post)
                        })
                }


        view.floatingActionButtonForPosts.setOnClickListener {


            var userId2 = arguments?.getInt("userId")

            var bundle = Bundle()
            if (userId2 == 0 || userId2 == null) {

                bundle.putInt("userId", userId)
            } else {

                if (userId2 != null) {
                    bundle.putInt("userId", userId2)
                }
            }
            findNavController().navigate(R.id.action_postListFragment2_to_addPostFragment, bundle)
        }
        setHasOptionsMenu(true)
        return view
        }catch (e: Exception){
            Log.e("hello", e.toString())
            throw e;
        }

    }
//
//    override fun onStart() {
//        super.onStart()
//        val adapter = PostListAdapter(uUserViewModel)
//
//        mPostViewModel.readAllPostsFromUser(1 ).observe(viewLifecycleOwner, Observer { post ->
//            adapter.setData(post)
//        })
//    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController()) ||
                return super.onOptionsItemSelected(item)
    }
}