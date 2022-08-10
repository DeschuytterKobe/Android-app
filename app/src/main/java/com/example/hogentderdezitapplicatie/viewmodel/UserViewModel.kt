package com.example.hogentderdezitapplicatie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hogentderdezitapplicatie.data.MainDatabase
import com.example.hogentderdezitapplicatie.repository.UserRepository
import com.example.hogentderdezitapplicatie.model.User
import com.example.hogentderdezitapplicatie.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {


    val db = MainDatabase.getDatabase(application.applicationContext)



    private val uRepository = UserRepository(db.userDao)

    val users = uRepository.readAllData


//    val readAllData: LiveData<List<User>>
//    private val repository : UserRepository



    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            uRepository.addUser(user)
        }
    }
    fun updateUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            uRepository.updateUser(user)
        }
    }

    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            uRepository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            uRepository.deleteAllUsers()
        }
    }

}