package com.example.oficialapp.data


import com.example.oficialapp.response.Cultivo
import retrofit2.Call
import retrofit2.http.*
interface CultivoService {

    @GET("cultivos")
    fun obtenerCultivos(): Call<List<Cultivo>>

    @POST("cultivos")
    fun crearCultivo(@Body cultivo: Cultivo): Call<Cultivo>

    @PUT("cultivos/{id}")
    fun actualizarCultivo(@Path("id") id: Int, @Body cultivo: Cultivo): Call<Cultivo>

    @DELETE("cultivos/{id}")
    fun eliminarCultivo(@Path("id") id: Int): Call<Void>

    @GET("cultivos/{id}")
    fun obtenerCultivoPorId(@Path("id") id: Int): Call<Cultivo>

}