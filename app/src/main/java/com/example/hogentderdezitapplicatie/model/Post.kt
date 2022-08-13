package com.example.hogentderdezitapplicatie.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "post_table")
class Post (

        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val userId: Int,
        val title: String,
        val description : String,
        val link : String,
        val postDate : Date?,
        var liked : Int,
        val picture : Bitmap,


        ) : Parcelable
