package com.educacionit.infoar.data.remote.service

import com.educacionit.infoar.data.remote.dto.NewsDTO
import com.educacionit.infoar.data.remote.dto.UserDTO
import retrofit2.http.GET

interface ServiceAPI {

    @GET("posts")
    suspend fun getNews(): List<NewsDTO>

    @GET("users")
    suspend fun getUsers(): List<UserDTO>
}