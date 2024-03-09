package com.example.searchimageapp.di

import com.example.searchimageapp.network.ApiService
import com.example.searchimageapp.ui.components.MainRepository
import com.example.searchimageapp.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideApiService():ApiService{
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }

    @Provides
    fun provideMainRepository(apiService : ApiService) : MainRepository{
        return MainRepository(apiService)
    }


}