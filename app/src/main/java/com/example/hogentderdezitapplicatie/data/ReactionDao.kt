package com.example.hogentderdezitapplicatie.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction

@Dao
interface ReactionDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun addReaction(reaction: Reaction)

        @Query("SELECT * FROM reaction_table ORDER BY id ASC")
        fun readAllReactions(): LiveData<List<Reaction>>

        @Query("SELECT * FROM reaction_table WHERE Postid = :key ORDER BY reactionDate ASC")
        fun getReactionsFromPost(key : Int): LiveData<List<Reaction>>


        @Query("SELECT * FROM reaction_table WHERE id = :key ")
        fun getReactionWithId(key : Int): LiveData<Reaction>

        @Update
        fun updateReaction(reaction: Reaction)

        @Delete
        fun deleteReaction(reaction: Reaction)

        @Query("SELECT COUNT(id) FROM reaction_table")
        fun getCount():Int
}