package com.sumit.daggerHiltStructure.ui.actvity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sumit.daggerHiltStructure.model.User
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = userRepository.getAllUsers().asLiveData()

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }
}
