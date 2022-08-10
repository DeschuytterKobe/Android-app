package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import com.example.hogentderdezitapplicatie.repository.PostRepository
import com.example.hogentderdezitapplicatie.repository.ReactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReactionViewModel(application: Application) : AndroidViewModel(application){
//    val db = MainDatabase.getDatabase(application.applicationContext)
//
//
//
//    private val rRepository = ReactionRepository(db.reactionDao)
//
//    val reaction = rRepository.readAllReactions
//
//
//    fun addReaction(reaction: Reaction){
//        viewModelScope.launch(Dispatchers.IO){
//            rRepository.addReaction(reaction)
//        }
//    }
//
//    fun updateReaction(reaction: Reaction){
//        viewModelScope.launch(Dispatchers.IO){
//            rRepository.updateReaction(reaction)
//        }
//    }
//
//    fun deleteReaction(reaction: Reaction){
//        viewModelScope.launch(Dispatchers.IO){
//            rRepository.deleteReaction(reaction)
//        }
//    }
//    fun getReactionsFromPost(postId : Int){
//        viewModelScope.launch(Dispatchers.IO){
//            rRepository.getReactionsFromPost(postId)
//        }
//    }


}