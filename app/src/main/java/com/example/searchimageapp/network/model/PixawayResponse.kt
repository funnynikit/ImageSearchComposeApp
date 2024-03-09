package com.example.searchimageapp.network.model

data class PixawayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)