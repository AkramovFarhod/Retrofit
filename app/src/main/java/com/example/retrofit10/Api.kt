package com.example.retrofit10

import com.example.retrofit10.model.ApiServeer
import com.example.retrofit10.model.ApiServeerItem
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/photos")
    fun getPosts(): Call<List<ApiServeerItem>>

}