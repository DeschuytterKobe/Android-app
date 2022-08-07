package com.example.hogentderdezitapplicatie.repository

import androidx.lifecycle.LiveData
import com.example.hogentderdezitapplicatie.data.UserDao
import com.example.hogentderdezitapplicatie.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao : UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
    fun searchDatabase(searchQuery: String): Flow<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }
}