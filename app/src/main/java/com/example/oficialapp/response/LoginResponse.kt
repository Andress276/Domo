package com.example.oficialapp.response

data class UserResponse(
    val message: String,
    val user: User // Modelo de datos para el usuario
)

data class LoginResponse(
    val message: String,
    val token: String,
    val user: User // Modelo de datos para el usuario
)

data class UserProfile(
    val name: String,
    val email: String,
    val password: String // Cantidad de campos actualizados, por ejemplo
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val profile_photo: String?, // Puede ser null
    // Otros campos del usuario según tu API
)

