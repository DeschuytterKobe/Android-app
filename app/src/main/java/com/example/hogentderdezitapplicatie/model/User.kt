package com.example.hogentderdezitapplicatie.model

import android.graphics.Bitmap
import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName: String,
    val lastName: String,
    val avatar: Int,
    //TODO val profilePhotoUri : String
    val profilePhotoUri :String,


    ) : Parcelable