package com.example.oficialapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oficialapp.data.RetrofitClientSen
import com.example.oficialapp.databinding.FragmentRegistrosBinding
import com.example.oficialapp.response.SensorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrosFragment : Fragment() {

    private lateinit var binding: FragmentRegistrosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración del RecyclerView
        val recyclerView = binding.recyclerViewDatos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Llamada a la API para obtener los datos
        obtenerDatosDeAPI()
    }

    private fun obtenerDatosDeAPI() {
        val apiService = RetrofitClientSen.create().getSensorData()

        apiService.enqueue(object : Callback<List<SensorData>> {
            override fun onResponse(call: Call<List<SensorData>>, response: Response<List<SensorData>>) {
                if (response.isSuccessful) {
                    val sensorDataList = response.body()
                    if (sensorDataList != null) {
                        // Procesar la lista de objetos SensorData obtenidos
                        mostrarDatosEnRecyclerView(sensorDataList)
                    } else {
                        Log.e("Retrofit", "Error: Respuesta vacía")
                    }
                } else {
                    Log.e("Retrofit", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SensorData>>, t: Throwable) {
                Log.e("Retrofit", "Error de conexión: ${t.message}")
            }
        })
    }

    private fun mostrarDatosEnRecyclerView(sensorDataList: List<SensorData>) {
        val adapter = SensorDataAdapter(sensorDataList)
        binding.recyclerViewDatos.adapter = adapter
    }
}


