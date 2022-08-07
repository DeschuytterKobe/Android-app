package com.example.hogentderdezitapplicatie.fragments.posts.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.model.Post
import kotlinx.android.synthetic.main.custom_row_post.view.*

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.MyPostViewHolder>() {

    private var postList = emptyList<Post>()

    class MyPostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {
        return MyPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_post,parent,false))
    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.itemView.postId_txt.text = currentItem.id.toString()
        holder.itemView.postTitle_txt.text = currentItem.title
        holder.itemView.postDescription_txt.text = currentItem.description
        holder.itemView.postLink_txt.text= currentItem.link

        holder.itemView.postRowLayout.setOnClickListener{
            val action = PostListFragmentDirections.actionPostListFragment2ToPostUpdateFragment(currentItem)
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