package com.example.hogentderdezitapplicatie.fragments.posts.open

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListFragmentDirections
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import kotlinx.android.synthetic.main.custom_reaction.view.*
import kotlinx.android.synthetic.main.custom_row_post.view.*

class ReactionListAdapter : RecyclerView.Adapter<ReactionListAdapter.MyReactionViewHolder>() {

    private var reactionList = emptyList<Reaction>()

    class MyReactionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReactionViewHolder {
        return MyReactionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_reaction,parent,false))
    }

    override fun onBindViewHolder(holder: MyReactionViewHolder, position: Int) {
        val currentItem = reactionList[position]
//        holder.itemView.postId_txt.text = currentItem.id.toString()
        holder.itemView.reaction_content.text = currentItem.content


    }

    fun setData(reaction: List<Reaction>){
        this.reactionList = reaction
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reactionList.size
    }
}