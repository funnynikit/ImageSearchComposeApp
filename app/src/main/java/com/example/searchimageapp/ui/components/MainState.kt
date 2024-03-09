package com.example.searchimageapp.ui.components

import com.example.searchimageapp.network.model.Hit

data class MainState(val isLoading : Boolean = false,
    val data : List<Hit>? = null,
    val error : String = ""
    )
