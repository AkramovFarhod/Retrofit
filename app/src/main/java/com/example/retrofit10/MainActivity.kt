package com.example.retrofit10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit10.model.ApiServeer
import com.example.retrofit10.model.ApiServeerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var listOfPosts by remember { mutableStateOf<List<ApiServeerItem>>(listOf()) }
            var error by remember {
                mutableStateOf("")
            }
            LaunchedEffect(key1 = false) {
                val retrofit = Retrofit
                    .Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val postApi = retrofit.create(Api::class.java)

                val call: Call<List<ApiServeerItem>> = postApi.getPosts()

                call.enqueue(object : Callback<List<ApiServeerItem>> {
                    override fun onResponse(
                        call: Call<List<ApiServeerItem>>,
                        response: Response<List<ApiServeerItem>>
                    ) {
                        if (response.isSuccessful) {
                            listOfPosts = response.body()!!
                        } else {
                            error = response.message()
                        }
                    }

                    override fun onFailure(call: Call<List<ApiServeerItem>>, t: Throwable) {
                        error = t.message.toString()
                    }
                })
            }
            if (error.isNotEmpty()) {
                Text(text = error)
            }
            LazyColumn(){
                items(listOfPosts){listss->
                    Text("name :"+listss.title)
                    Text("email :"+listss.url)
                    Text("phone :"+listss.thumbnailUrl)
                    Text("username :"+listss.id)
                    Text("address :"+listss.albumId)
                    Divider()
                }
                }
            }
        }
    }

