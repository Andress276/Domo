package com.example.oficialapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.response.SensorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SensoresFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var sensorAdapter: SensorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sensores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        sensorAdapter = SensorAdapter(emptyList()) // Inicializa con una lista vacía
        recyclerView.adapter = sensorAdapter

        obtenerDatosDeAPI()
    }

    private fun obtenerDatosDeAPI() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://domofticaweb-production.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.obtenerDatos()

        call.enqueue(object : Callback<List<SensorData>> {
            override fun onResponse(
                call: Call<List<SensorData>>,
                response: Response<List<SensorData>>
            ) {
                if (response.isSuccessful) {
                    val datos = response.body()
                    datos?.let {
                        mostrarDatosEnRecyclerView(it)
                    }
                } else {
                    mostrarMensajeError()
                }
            }

            override fun onFailure(call: Call<List<SensorData>>, t: Throwable) {
                mostrarMensajeError()
            }
        })
    }

    private fun mostrarDatosEnRecyclerView(sensorDataList: List<SensorData>) {
        sensorAdapter = SensorAdapter(sensorDataList)
        recyclerView.adapter = sensorAdapter
    }

    private fun mostrarMensajeError() {
        Toast.makeText(requireContext(), "Error al obtener datos", Toast.LENGTH_SHORT).show()
    }
}
