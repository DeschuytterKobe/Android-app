package com.example.hogentderdezitapplicatie.fragments.posts.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
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
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_list.view.*

class PostListFragment : Fragment() {

    private lateinit var mPostViewModel : PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var userId = SecureFileHandle(context,  AuthTokenSecureFile()).file.userId


        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        val adapter = PostListAdapter()
        val recyclerView = view.postRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mPostViewModel.postsFromUser.observe(viewLifecycleOwner, Observer { post ->
            adapter.setData(post)
        })

        view.floatingActionButtonForPosts.setOnClickListener{
            Log.d("userid1",userId.toString())

            var userId2 = arguments?.getInt("userId")
            Log.d("userid2",userId2.toString())
            var bundle = Bundle()
            if(userId2 == 0 || userId2 == null) {

                bundle.putInt("userId", userId)
            }else{

                if (userId2 != null) {
                    bundle.putInt("userId",userId2)
                }
            }
            findNavController().navigate(R.id.action_postListFragment2_to_addPostFragment,bundle)
        }
        setHasOptionsMenu(true)
        return view

    }
    override fun onStart(){
        super.onStart()
        val adapter = PostListAdapter()

        mPostViewModel.postsFromUser.observe(viewLifecycleOwner, Observer { post ->
            adapter.setData(post)
        })
    }
//    override fun onResume(){
//
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())||
        return super.onOptionsItemSelected(item)
    }
}