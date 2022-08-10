package com.example.hogentderdezitapplicatie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.User

@Database(entities = [Post::class, User::class], version=2, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract val postDao : PostDao
    abstract val userDao : UserDao


    companion object{
        @Volatile
        private var INSTANCE : MainDatabase?= null

        fun getDatabase(context: Context): MainDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}