package com.sumit.daggerHiltStructure.network

import com.sumit.daggerHiltStructure.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
