package com.sumit.daggerHiltStructure.ui.actvity.main

import com.sumit.daggerHiltStructure.model.User
import com.sumit.daggerHiltStructure.model.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun insertUser(user: User): Long {
        return userDao.insert(user)
    }
}
