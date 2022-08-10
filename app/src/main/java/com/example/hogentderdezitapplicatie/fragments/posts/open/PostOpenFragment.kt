package com.example.hogentderdezitapplicatie.fragments.posts.open

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.fragments.posts.update.PostUpdateFragmentArgs
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.ReactionViewModel
import kotlinx.android.synthetic.main.fragment_post_list.view.*
import kotlinx.android.synthetic.main.fragment_post_open.view.*
import kotlinx.android.synthetic.main.fragment_post_update.view.*
import kotlinx.android.synthetic.main.fragment_post_update.view.update_postLink


class PostOpenFragment : Fragment() {

    private val args by navArgs<PostOpenFragmentArgs>()
    private lateinit var mPostViewModel : PostViewModel
//    private lateinit var rReactionViewModel : ReactionViewModel
//    private var rrrr = rReactionViewModel.getReactionsFromPost(args.openCurrentPost.id)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_open, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        view.open_postTitle.setText(args.openCurrentPost.title)
        view.open_postDescription.setText(args.openCurrentPost.description)
        view.open_postLink.setText(args.openCurrentPost.link)
        setHasOptionsMenu(true)


//        view.EditPostButton.setOnClickListener{
//            findNavController().navigate(R.id.action_postOpenFragment_to_postUpdateFragment)
//        }
        return view
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