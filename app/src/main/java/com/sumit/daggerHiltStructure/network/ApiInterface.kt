package com.sumit.daggerHiltStructure.network

import com.sumit.daggerHiltStructure.model.post
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getPost():List<post>
}