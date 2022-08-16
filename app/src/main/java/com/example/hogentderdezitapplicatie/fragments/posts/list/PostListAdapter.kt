package com.example.hogentderdezitapplicatie.fragments.posts.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hogentderdezitapplicatie.R

import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row_post.view.*
import kotlinx.android.synthetic.main.custom_row_post.view.postlist_user_firstname
import kotlinx.android.synthetic.main.custom_row_post.view.postlist_user_profilephoto

import java.text.SimpleDateFormat
import java.util.*

class PostListAdapter(userViewModel: UserViewModel) : RecyclerView.Adapter<PostListAdapter.MyPostViewHolder>() {

    private var postList = emptyList<Post>()
    private lateinit var mPostViewModel: PostViewModel
    private  var uUserViewModel= userViewModel

    class MyPostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        return MyPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_post,parent,false))


    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {


        val currentItem = postList[position]

//        uUserViewModel.getUserByIdForList.observe(viewLifecycleOwner, Observer { post ->
//            adapter.setData(post)
////        })
//        val user = uUserViewModel.getUserByIdForList(currentItem.userId)

//        holder.itemView.postlist_user_firstname.text = user.firstName
//        holder.itemView.postlist_user_profilephoto.setImageBitmap(user.profilePhoto)
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(currentItem.postDate)
        holder.itemView.postTitle_txt.text = currentItem.title
        holder.itemView.postDescription_txt.text = currentItem.description
        holder.itemView.postDate_txt.text= currentDate
        if(currentItem.liked==1){
          
            holder.itemView.postLikeButton.isVisible = true
        }else  holder.itemView.postLikeButton.isVisible = false
        if(currentItem.read){
            holder.itemView.postlist_seen_image.isVisible=true
        }else holder.itemView.postlist_seen_image.isVisible=false
        if(currentItem.answered){
            holder.itemView.postlist_answered_image.isVisible=true
        }else holder.itemView.postlist_answered_image.isVisible=false

//        holder.itemView.postLikeButton.setOnClickListener{
//            val updatePost = Post(currentItem.id,currentItem.userId,currentItem.title,currentItem.description,currentItem.link,currentItem.postDate,1,currentItem.picture)
//
//            mPostViewModel.updatePost(updatePost)
//            Log.d("in postlistadapter","liked the post")
//        }

        holder.itemView.postRowLayout.setOnClickListener{
            val action = PostListFragmentDirections.actionPostListFragment2ToPostOpenFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }



    fun setData(post: List<Post>){
        this.postList = post
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}