package com.example.oficialapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.R
import com.example.oficialapp.adapter.SensorDataAdapter
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.response.SensorData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: SensorDataAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registros, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewDatos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SensorDataAdapter()

        recyclerView.adapter = adapter

        fetchDataFromApi()

        return view
    }

    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://domofticaweb-production.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getSensorData()

        call.enqueue(object : Callback<List<SensorData>> {
            override fun onResponse(call: Call<List<SensorData>>, response: Response<List<SensorData>>) {
                if (response.isSuccessful) {
                    val sensorDataList = response.body()
                    sensorDataList?.let {
                        adapter?.setData(it)
                    }
                } else {
                    // Manejar errores de respuesta aquí
                }
            }

            override fun onFailure(call: Call<List<SensorData>>, t: Throwable) {
                // Manejar errores de conexión aquí
                Log.e("API_CALL", "Error al obtener datos: ${t.message}")
            }
        })
    }
}
