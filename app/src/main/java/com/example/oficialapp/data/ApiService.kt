package com.example.oficialapp.data

import com.example.oficialapp.response.LoginResponse
import com.example.oficialapp.response.UserProfile
import com.example.oficialapp.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/users")
    fun registerUser(
        @Body userData: HashMap<String, String>
    ): Call<UserResponse> // Define UserResponse según la estructura de tu respuesta

    @POST("api/login")
    fun login(
        @Body credentials: HashMap<String, String>
    ): Call<LoginResponse> // Define LoginResponse según la estructura de tu respuesta

    @GET("api/profile")
    fun getProfile(@Header("Authorization") token: String): Call<UserProfile>

    @PUT("api/profile")
    fun updateProfile(@Header("Authorization") token: String, @Body updatedProfile: UserProfile): Call<UserProfile>
}