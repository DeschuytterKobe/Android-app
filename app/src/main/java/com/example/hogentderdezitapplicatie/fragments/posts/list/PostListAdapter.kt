package com.example.hogentderdezitapplicatie.fragments.posts.list


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hogentderdezitapplicatie.R
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row_post.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat


class PostListAdapter(userViewModel: UserViewModel) :
    RecyclerView.Adapter<PostListAdapter.MyPostViewHolder>() {

    private var postList = emptyList<Post>()
    private lateinit var mPostViewModel: PostViewModel
    private var uUserViewModel = userViewModel

    private lateinit var context: Context

    class MyPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {

        context = parent.context
        return MyPostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row_post, parent, false)
        )


    }

    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {
        val currentItem = postList[position]
        holder.itemView.postRowLayout.setOnClickListener {
            val action =
                PostListFragmentDirections.actionPostListFragment2ToPostOpenFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }

        GlobalScope.launch {
            val user = uUserViewModel.getUserByIdForList(currentItem.userId)
            withContext(Dispatchers.Main) {
                holder.itemView.postlist_user_firstname.text = user.firstName
            }
        }
        val sdf = SimpleDateFormat("dd/M/yyyy : HH:mm")
        val currentDate = sdf.format(currentItem.postDate)
        holder.itemView.postDescription_txt.text = currentItem.description
        holder.itemView.postDate_txt.text = currentDate
        if (SecureFileHandle(context, AuthTokenSecureFile()).file.userId == 3) {

            holder.itemView.postLikeButton.isVisible = false
            holder.itemView.postlist_seen_image.isVisible = currentItem.read
            holder.itemView.postlist_answered_image.isVisible = currentItem.answered
        } else {
            holder.itemView.postLikeButton.isVisible = currentItem.liked == 1
            holder.itemView.postlist_seen_image.isVisible = currentItem.read
            holder.itemView.postlist_answered_image.isVisible = currentItem.answered
        }
    }


    fun setData(post: List<Post>) {
        this.postList = post
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return postList.size
    }


}