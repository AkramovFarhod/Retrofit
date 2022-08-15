package com.example.retrofit10

import com.example.retrofit10.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/posts")
    fun getPosts(): Call<List<Posts>>
}