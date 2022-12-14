package com.example.hogentderdezitapplicatie.repository

import androidx.lifecycle.LiveData
import com.example.hogentderdezitapplicatie.data.ReactionDao
import com.example.hogentderdezitapplicatie.model.Reaction

class ReactionRepository(private val reactionDao: ReactionDao) {

    //        var readSpecific : LiveData<List<Reaction>> = reactionDao.getReactionsFromPost(1)
    val readAllReactions: LiveData<List<Reaction>> = reactionDao.readAllReactions()

    suspend fun addReaction(reaction: Reaction) {
        reactionDao.addReaction(reaction)
    }

    fun getReactionsFromPost(postId: Int): LiveData<List<Reaction>> {
        return reactionDao.getReactionsFromPost(postId)
    }

    suspend fun updateReaction(reaction: Reaction) {
        reactionDao.updateReaction(reaction)
    }

    suspend fun deleteReaction(reaction: Reaction) {
        reactionDao.deleteReaction(reaction)
    }
}
