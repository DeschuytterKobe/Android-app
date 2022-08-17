package com.example.hogentderdezitapplicatie.fragments.posts.open

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListAdapter
import com.example.hogentderdezitapplicatie.fragments.posts.list.PostListFragmentDirections
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import com.example.hogentderdezitapplicatie.viewmodel.ReactionViewModel
import kotlinx.android.synthetic.main.custom_reaction.view.*
import kotlinx.android.synthetic.main.custom_row_post.view.*
import kotlinx.android.synthetic.main.fragment_post_open.view.*

class ReactionListAdapter(reactionViewModel: ReactionViewModel, context : Context) : RecyclerView.Adapter<ReactionListAdapter.MyReactionViewHolder>() {

    private var reactionList = emptyList<Reaction>()
    private  var rReactionViewModel = reactionViewModel
    private var contextHere = context
    class MyReactionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReactionViewHolder {
        return MyReactionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_reaction,parent,false))
    }

    override fun onBindViewHolder(holder: MyReactionViewHolder, position: Int) {
        val currentItem = reactionList[position]

//        holder.itemView.postId_txt.text = currentItem.id.toString()
        holder.itemView.setBackgroundColor(Color.rgb(177, 187, 227))


        if(currentItem.userId==3){
            holder.itemView.setBackgroundColor(Color.rgb(227, 217, 177))

        }
        holder.itemView.reaction_content.setText(currentItem.content)
      if(currentItem.userId == SecureFileHandle(contextHere,  AuthTokenSecureFile()).file.userId ){
          holder.itemView.post_reaction_edit_btn.isVisible=true
          holder.itemView.post_reaction_delete_btn.isVisible= true
      }else{
          holder.itemView.post_reaction_edit_btn.isVisible=false
          holder.itemView.post_reaction_delete_btn.isVisible= false
      }

        holder.itemView.post_reaction_edit_btn.setOnClickListener{

            val newContent = holder.itemView.reaction_content.text

            val newReaction = Reaction(currentItem.id,currentItem.postId,currentItem.userId,newContent.toString())
            editReaction(newReaction)
            holder.itemView.reaction_content.setText(newContent)
        }
        holder.itemView.post_reaction_delete_btn.setOnClickListener{
            deleteReaction(currentItem)
        }


    }
    fun deleteReaction(reaction : Reaction){
        val builder = AlertDialog.Builder(contextHere)
        builder.setPositiveButton("yes"){
                _,_ -> rReactionViewModel.deleteReaction(reaction)
            Toast.makeText(contextHere,"Succesfully removed",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("no"){
                _,_ ->

        }
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure you want to delete ?")
        builder.create().show(

        )

    }

    fun editReaction(reaction : Reaction){
        rReactionViewModel.updateReaction(reaction)
    }

    fun setData(reaction: List<Reaction>){
        this.reactionList = reaction
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reactionList.size
    }
}