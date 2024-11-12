package com.sumit.daggerHiltStructure.network

import com.sumit.daggerHiltStructure.model.post
import javax.inject.Inject

// NKHL_4
//implementation declare in  inject in dragger hill  -> Application class @HilltAndroid

class ApiInterfaceImp @Inject
constructor(private val api:ApiInterface){
    suspend fun getPost():List<post> =api.getPost()
}