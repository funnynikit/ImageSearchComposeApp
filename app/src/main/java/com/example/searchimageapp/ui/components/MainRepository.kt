package com.example.searchimageapp.ui.components

import com.example.searchimageapp.network.ApiService
import com.example.searchimageapp.network.model.PixawayResponse
import com.example.searchimageapp.utils.Constant
import com.example.searchimageapp.utils.Resource
import javax.inject.Inject

class  MainRepository @Inject constructor(private val apiService : ApiService) {

    suspend fun getFlowersQuery(q : String) : Resource<PixawayResponse>{
        return try {
                val result = apiService.getFlowersResponse(query = q, apiKey = Constant.KEY, imageType = "photo")
                Resource.Success(data = result)
        }
        catch (e : Exception)
        {
            Resource.Error(message = e.message.toString())
        }
    }
}