package com.educacionit.infoar.repository.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceApiProvider {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val service: ServiceAPI by lazy {
        getRetrofitInstance(BASE_URL).create(ServiceAPI::class.java)
    }

    private fun getRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}