package com.example.hogentderdezitapplicatie.fragments.posts.open

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle

import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.fragments.posts.update.PostUpdateFragmentArgs
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.utils.SpacingItemsDecorator
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.ReactionViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import kotlinx.android.synthetic.main.fragment_post_open.*
import kotlinx.android.synthetic.main.fragment_post_open.view.*
import kotlinx.android.synthetic.main.fragment_post_update.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*
import kotlinx.android.synthetic.main.fragment_post_update.view.update_postLink
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.coroutines.launch
import java.util.*


class PostOpenFragment : Fragment() {

    private val args by navArgs<PostOpenFragmentArgs>()
    private lateinit var mPostViewModel : PostViewModel
    private lateinit var rReactionViewModel : ReactionViewModel
    private var reactionList = emptyList<Reaction>()
    private lateinit var spacingItemsDecorator : SpacingItemsDecorator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_open, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        rReactionViewModel = ViewModelProvider(this).get(ReactionViewModel::class.java)

        val adapter = ReactionListAdapter(rReactionViewModel,requireContext())

        spacingItemsDecorator =  SpacingItemsDecorator(10)
        val recyclerView = view.reactionRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(spacingItemsDecorator)

        if(SecureFileHandle(context,  AuthTokenSecureFile()).file.userId==3){
            updatePost(false)
        }



        rReactionViewModel.getReactionsFromPost(args.openCurrentPost.id).observe(viewLifecycleOwner, Observer { reaction ->

            adapter.setData(reaction)
        })



        view.open_postTitle.setText(args.openCurrentPost.title)
        view.open_postDescription.setText(args.openCurrentPost.description)

        view.open_postLink.setText(args.openCurrentPost.link)
        view.img_open_post.load(args.openCurrentPost.picture)

        view.AddReactionButton.setOnClickListener {
            insertDataToDatabase()
        }

        view.open_post_like.setOnClickListener{
            likePost()
            Log.d("clcked on post like","yeet")
        }



        setHasOptionsMenu(true)


        view.EditPostButton.setOnClickListener{
            var bundle = Bundle()
            bundle.putInt("postId", args.openCurrentPost.id)
            findNavController().navigate(R.id.action_postOpenFragment_to_postUpdateFragment, bundle)
        }
        return view
    }

    private fun likePost(){

        lifecycleScope.launch{
            val updatesPost = Post(args.openCurrentPost.id,SecureFileHandle(context,  AuthTokenSecureFile()).file.userId,
                args.openCurrentPost.title,
                args.openCurrentPost.description,
                args.openCurrentPost.link,Date(),
                1,
                args.openCurrentPost.picture,
            args.openCurrentPost.read,
            args.openCurrentPost.answered)
            mPostViewModel.updatePost(updatesPost)
        }


        findNavController().navigate(R.id.action_postOpenFragment_to_postListFragment2)



            }
    private fun updatePost(answered : Boolean){
if(answered){
    lifecycleScope.launch{
        val updatesPost = Post(args.openCurrentPost.id,SecureFileHandle(context,  AuthTokenSecureFile()).file.userId,
            args.openCurrentPost.title,
            args.openCurrentPost.description,
            args.openCurrentPost.link,Date(),
            args.openCurrentPost.liked,
            args.openCurrentPost.picture,
            true,
            true)
        mPostViewModel.updatePost(updatesPost)
    }
}else {
    lifecycleScope.launch {
        val updatesPost = Post(
            args.openCurrentPost.id, SecureFileHandle(context, AuthTokenSecureFile()).file.userId,
            args.openCurrentPost.title,
            args.openCurrentPost.description,
            args.openCurrentPost.link, Date(),
            args.openCurrentPost.liked,
            args.openCurrentPost.picture,
            true,
            args.openCurrentPost.answered
        )
        mPostViewModel.updatePost(updatesPost)
    }

}





    }

    private fun insertDataToDatabase() {
        val reactionContent = add_reactionContent.text.toString()


        if(inputCheck(reactionContent)){
            // Create User Object
            val reaction = Reaction(0,SecureFileHandle(context,  AuthTokenSecureFile()).file.userId,args.openCurrentPost.id,reactionContent)
            // Add Data to Database
            rReactionViewModel.addReaction(reaction)
            if(SecureFileHandle(context,  AuthTokenSecureFile()).file.userId==3){
                updatePost(true)
            }
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_postOpenFragment_to_postListFragment2)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }
    private fun inputCheck(reactionContent: String): Boolean{
        return !(TextUtils.isEmpty(reactionContent))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deletePost()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePost(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){
                _,_ -> mPostViewModel.deletePost(args.openCurrentPost)
            Toast.makeText(requireContext(),"Succesfully removed: ${args.openCurrentPost.title}",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("no"){
                _,_ ->

        }
        builder.setTitle("Delete ${args.openCurrentPost.title}?")
        builder.setMessage("Are you sure you want to delete ${args.openCurrentPost.title}?")
        builder.create().show(

        )
    }

}

