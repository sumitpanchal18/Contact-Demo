package com.sumit.daggerHiltStructure.ui.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val users = repository.getAllUsers()

    fun addUser(user: User) {
        viewModelScope.launch {
            try {
                repository.addUser(user)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error adding user: ${e.message}")
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                repository.updateUser(user)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error updating user: ${e.message}")
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            try {
                repository.deleteUser(user)
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error deleting user: ${e.message}")
            }
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            try {
                repository.deleteAllUsers()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error deleting all users: ${e.message}")
            }
        }
    }

    fun refreshUsers() {
        viewModelScope.launch {
            try {
                repository.refreshUsers()
            } catch (e: Exception) {
                Log.d(TAG, "Failed to refresh users")
            }
        }
    }

    fun fetchDeletedUsers() {
        viewModelScope.launch {
            try {
                repository.fetchDeletedUsers()
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching deleted users: ${e.message}")
            }
        }
    }
}