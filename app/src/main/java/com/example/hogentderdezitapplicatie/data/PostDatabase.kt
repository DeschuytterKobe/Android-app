package com.example.hogentderdezitapplicatie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hogentderdezitapplicatie.model.Post

@Database(entities = [Post::class], version=1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {

    abstract  fun postDao(): PostDao

    companion object{
        @Volatile
        private var INSTANCE : PostDatabase?= null

        fun getDatabase(context: Context): PostDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "post_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}