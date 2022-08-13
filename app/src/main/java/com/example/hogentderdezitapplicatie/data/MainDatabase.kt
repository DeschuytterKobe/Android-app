package com.example.hogentderdezitapplicatie.data

import android.content.Context
import androidx.room.*
import com.example.hogentderdezitapplicatie.converters.Converters
import com.example.hogentderdezitapplicatie.model.Post
import com.example.hogentderdezitapplicatie.model.Reaction
import com.example.hogentderdezitapplicatie.model.User

@Database(entities = [Post::class, User::class,Reaction::class], version=2,exportSchema = false )
@TypeConverters(Converters::class)
abstract class MainDatabase : RoomDatabase() {

    abstract val postDao : PostDao
    abstract val userDao : UserDao
    abstract val reactionDao : ReactionDao


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
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}