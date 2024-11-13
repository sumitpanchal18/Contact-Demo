package com.sumit.daggerHiltStructure.network

import com.sumit.daggerHiltStructure.ui.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}