package com.example.searchimageapp.utils

sealed class Resource<T> (val myData: T? = null, val msg: String? = null){
        class Loading<T>() : Resource<T>()
        class Error<T>(message: String) : Resource<T>(msg = message)
        class Success<T>(data: T?) : Resource<T>(myData = data)
}