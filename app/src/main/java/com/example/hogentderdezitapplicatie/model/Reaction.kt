package com.example.hogentderdezitapplicatie.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "reaction_table")
class Reaction (

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userId: Int,
    val postId: Int,
    val content: String,
    val reactionDate : Date,



    ) : Parcelable