package com.sumit.daggerHiltStructure.network

import com.sumit.daggerHiltStructure.ui.model.User
import com.sumit.daggerHiltStructure.utils.Constant
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.USER_END_POINT)
    suspend fun getUsers(): List<User>
}