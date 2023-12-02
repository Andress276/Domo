package com.example.oficialapp.data

import com.example.oficialapp.response.LoginResponse
import com.example.oficialapp.response.SensorData
import com.example.oficialapp.response.UserProfile
import com.example.oficialapp.response.UserResponse
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/users")
    fun registerUser(
        @Body userData: HashMap<String, String>
    ): Call<UserResponse>

    @POST("api/login")
    fun login(
        @Body credentials: HashMap<String, String>
    ): Call<LoginResponse>

    @GET("api/profile")
    fun getProfile(@Header("Authorization") token: String): Call<UserProfile>

    @PUT("api/profile")
    fun updateProfile(@Header("Authorization") token: String, @Body updatedProfile: UserProfile): Call<UserProfile>

    @GET("sensor")
    fun getSensorData(): Call<List<SensorData>>
}

