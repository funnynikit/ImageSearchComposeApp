package com.example.searchimageapp.network

import android.media.Image
import com.example.searchimageapp.network.model.Hit
import com.example.searchimageapp.network.model.PixawayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getFlowersResponse(
        @Query("q") query:String,
        @Query("key") apiKey:String,
        @Query("image_type") imageType:String
    ) : PixawayResponse


}