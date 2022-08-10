package com.example.hogentderdezitapplicatie.repository

import androidx.lifecycle.LiveData
import com.example.hogentderdezitapplicatie.data.PostDao
import com.example.hogentderdezitapplicatie.data.ReactionDao
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction

class ReactionRepository (private val reactionDao : ReactionDao){


//        val readAllReactions : LiveData<List<Reaction>> = reactionDao.readAllReactions()

        suspend fun addReaction(reaction: Reaction){
            reactionDao.addReaction(reaction)
        }

//        suspend fun getReactionsFromPost(postId : Int){
//            reactionDao.getReactionsFromPost(postId)
//        }

        suspend fun updateReaction(reaction: Reaction){
            reactionDao.updateReaction(reaction)
        }
        suspend fun deleteReaction(reaction: Reaction){
            reactionDao.deleteReaction(reaction)
        }
    }
