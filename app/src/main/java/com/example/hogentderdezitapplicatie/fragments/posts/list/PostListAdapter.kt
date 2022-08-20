package com.example.hogentderdezitapplicatie.fragments.posts.list


import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.view.isVisible


import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.hogentderdezitapplicatie.R

import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.viewmodel.PostViewModel
import com.example.hogentderdezitapplicatie.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custom_row_post.view.*
import java.lang.Exception


import java.text.SimpleDateFormat
import java.util.*


import android.graphics.BitmapFactory
import android.widget.ImageView
import com.example.hogentderdezitapplicatie.domein.AuthTokenSecureFile
import java.io.File
import java.io.FileInputStream
import com.example.hogentderdezitapplicatie.domein.ImageSaver
import com.example.hogentderdezitapplicatie.domein.SecureFileHandle
import com.example.hogentderdezitapplicatie.model.User
import kotlinx.coroutines.*
import okhttp3.internal.wait


class PostListAdapter(userViewModel: UserViewModel) :
    RecyclerView.Adapter<PostListAdapter.MyPostViewHolder>() {

    private var postList = emptyList<Post>()
    private lateinit var mPostViewModel: PostViewModel
    private var uUserViewModel = userViewModel

    private lateinit var context: Context;

    class MyPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostViewHolder {

      context = parent.context;
        return MyPostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row_post, parent, false)
        )


    }




    override fun onBindViewHolder(holder: MyPostViewHolder, position: Int) {


        val currentItem = postList[position]

//        uUserViewModel.getUserByIdForList.observe(viewLifecycleOwner, Observer { post ->
//            adapter.setData(post)
////        })

//        holder.itemView.postLikeButton.setOnClickListener{
//            val updatePost = Post(currentItem.id,currentItem.userId,currentItem.title,currentItem.description,currentItem.link,currentItem.postDate,1,currentItem.picture,currentItem.read,currentItem.answered)
//            Thread{
//                mPostViewModel.updatePost(updatePost)
//            }.start()
//
//
//        }

        holder.itemView.postRowLayout.setOnClickListener {
            val action =
                PostListFragmentDirections.actionPostListFragment2ToPostOpenFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }
        // CoroutineScope(Dispatchers.Default).launch {


        GlobalScope.launch {
            val user = uUserViewModel.getUserByIdForList(currentItem.userId)
            withContext(Dispatchers.Main){
                holder.itemView.postlist_user_firstname.text = user.firstName
            }
        }

//            Thread{
//
//
////                    laad file van uripath door user.profilePhoteUri
//
////                    val bitmap =
////                        ImageSaver(context).setFileName(user.profilePhotoUri).setDirectoryName("images")
////                            .load()
////                    holder.itemView.postlist_user_profilephoto.setImageBitmap(bitmap)
//
//
//
//
//            }.start()



                    val sdf = SimpleDateFormat("dd/M/yyyy : HH:mm")
                    val currentDate = sdf.format(currentItem.postDate)
                    //holder.itemView.postTitle_txt.text = currentItem.title
                    holder.itemView.postDescription_txt.text = currentItem.description
                    holder.itemView.postDate_txt.text = currentDate
        if(SecureFileHandle(context,  AuthTokenSecureFile()).file.userId==3){
            holder.itemView.postLikeButton.isVisible=false
            if (currentItem.read) {
                holder.itemView.postlist_seen_image.isVisible = true
            } else holder.itemView.postlist_seen_image.isVisible = false
            if (currentItem.answered) {
                holder.itemView.postlist_answered_image.isVisible = true
            } else holder.itemView.postlist_answered_image.isVisible = false
        }else{
                    if (currentItem.liked == 1) {

                        holder.itemView.postLikeButton.isVisible = true
                    } else holder.itemView.postLikeButton.isVisible = false
                    if (currentItem.read) {
                        holder.itemView.postlist_seen_image.isVisible = true
                    } else holder.itemView.postlist_seen_image.isVisible = false
                    if (currentItem.answered) {
                        holder.itemView.postlist_answered_image.isVisible = true
                    } else holder.itemView.postlist_answered_image.isVisible = false



//                } catch (e: Exception) {
//                    print(e)
//                }
//            }.start();
//        } catch (e: Exception) {
//            print(e)
//        }


        //    }

    }}


    fun setData(post: List<Post>) {
        this.postList = post
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return postList.size
    }


}