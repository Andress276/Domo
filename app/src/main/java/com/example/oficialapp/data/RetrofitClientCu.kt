package com.example.oficialapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientCu {

    private const val BASE_URL = "https://domofticaweb-production.up.railway.app/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cultivoService: CultivoService by lazy {
        retrofit.create(CultivoService::class.java)
    }

}