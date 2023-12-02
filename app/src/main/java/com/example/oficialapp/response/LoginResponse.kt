package com.example.oficialapp.response

import java.time.LocalDateTime

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
    val password: String // Cantidad de campos actualizados
)

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val profile_photo: String?,

)

data class Cultivo(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val nivel_humedad_optimo: Int,
    val tipo_plantas: String,
    val fecha_siembra: String

)

data class SensorData(
    val id: Int,
    val humedad: Int,
    val temperatura: Int,
    val humedad_suelo: Int,
    val flujo_agua: Int,
    val fecha_hora: String,
    val created_at: String,
    val updated_at: String
)



