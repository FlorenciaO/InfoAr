package com.educacionit.infoar.repository.service

import com.educacionit.infoar.NewsDTO
import com.educacionit.infoar.UserDTO
import retrofit2.http.GET

interface ServiceAPI {

    @GET("posts")
    suspend fun getNews(): List<NewsDTO>

    @GET("users")
    suspend fun getUsers(): List<UserDTO>
}