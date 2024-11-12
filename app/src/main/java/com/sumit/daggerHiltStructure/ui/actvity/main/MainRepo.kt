package com.sumit.daggerHiltStructure.ui.actvity.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.sumit.daggerHiltStructure.model.post
import com.sumit.daggerHiltStructure.network.ApiInterfaceImp
import javax.inject.Inject

//NKHL_10

class MainRepo
@Inject
constructor(private val api:ApiInterfaceImp)
{
    //flow = data on server to fetch  asynchronously  / hold data on one time and throw the data one by one (emit)

    fun getPost(): Flow<List<post>> =  flow {
        emit(api.getPost())
    }.flowOn(Dispatchers.IO)

}