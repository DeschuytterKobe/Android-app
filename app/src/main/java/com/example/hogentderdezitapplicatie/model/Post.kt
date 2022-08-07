package com.example.hogentderdezitapplicatie.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "post_table")
class Post (

        @PrimaryKey(autoGenerate = true)
        val id:Int,
//        val userId: Int,
        val title: String,
        val description : String,
        val link : String,


        ) : Parcelable
