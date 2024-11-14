package com.sumit.daggerHiltStructure.ui.repo

import android.content.Context
import android.util.Log
import com.sumit.daggerHiltStructure.network.ApiService
import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.ui.model.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val context: Context,
    private val userDao: UserDao,
    private val apiService: ApiService
) {
    private val deletedUserIds: MutableSet<Long> = mutableSetOf()

    init {
        loadDeletedUserIds()
    }

    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        try {
            userDao.insertUser(user)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error adding user: ${e.message}")
        }
    }

    suspend fun updateUser(user: User) {
        try {
            userDao.updateUser(user)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error updating user: ${e.message}")
        }
    }

    suspend fun deleteUser(user: User) {
        try {
            userDao.deleteUser(user)
            Log.d("UserRepository", "User deleted and ID stored: ${user.id}")
        } catch (e: Exception) {
            Log.e("UserRepository", "Error deleting user: ${e.message}")
        }
        deletedUserIds.add(user.id)
        saveDeletedUserIds()
    }

    suspend fun deleteAllUsers() {
        try {
            userDao.deleteAllUsers()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error deleting all users: ${e.message}")
        }
    }

    suspend fun fetchDeletedUsers() {
        try {
            Log.d("UserRepository", "Current deleted IDs: $deletedUserIds")

            val users = apiService.getUsers()
            Log.d("UserRepository", "API returned ${users.size} users")

            val existingUsers = userDao.getAllUsers().first()
            Log.d("UserRepository", "DB has ${existingUsers.size} users")

            val usersToRestore = users.filter { apiUser ->
                val shouldRestore = deletedUserIds.contains(apiUser.id) &&
                        existingUsers.none { dbUser -> dbUser.id == apiUser.id }
                shouldRestore
            }

            if (usersToRestore.isNotEmpty()) {
                Log.d("UserRepository", "Found ${usersToRestore.size} users to restore")
                userDao.insertUsers(usersToRestore)

                deletedUserIds.removeAll(usersToRestore.map { it.id }.toSet())
                saveDeletedUserIds()
            } else {
                Log.d("UserRepository", "No users to restore")
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching deleted users", e)
            throw e
        }
    }

    suspend fun refreshUsers() {
        try {
            val users = apiService.getUsers()
            userDao.insertUsers(users)
        } catch (e: Exception) {
            throw e
        }
    }

    private fun saveDeletedUserIds() {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putStringSet("deleted_user_ids", deletedUserIds.map { it.toString() }.toSet())
            apply()
        }
    }

    private fun loadDeletedUserIds() {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val savedIds = prefs.getStringSet("deleted_user_ids", emptySet()) ?: emptySet()
        deletedUserIds.clear()
        deletedUserIds.addAll(savedIds.map { it.toLong() })
        Log.d("UserRepository", "Loaded deleted IDs: $deletedUserIds")
    }


}