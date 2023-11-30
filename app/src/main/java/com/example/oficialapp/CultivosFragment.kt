package com.example.oficialapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.data.CultivoService
import com.example.oficialapp.data.RetrofitClient
import com.example.oficialapp.data.RetrofitClientCu
import com.example.oficialapp.response.Cultivo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CultivosFragment : Fragment() {

    private lateinit var cultivoService: CultivoService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cultivos, container, false)

        // Inicializar cultivoService
        cultivoService = RetrofitClientCu.cultivoService

        val editTextNombreNuevo = view.findViewById<EditText>(R.id.editTextNombreNuevo)
        val editTextDescripcionNuevo = view.findViewById<EditText>(R.id.editTextDescripcionNuevo)
        val editTextNivelHumedadOptimoNuevo = view.findViewById<EditText>(R.id.editTextNivelHumedadOptimoNuevo)
        val editTextTipoDePlantasNuevo = view.findViewById<EditText>(R.id.editTextTipoDePlantasNuevo)
        val editTextFechaSiembraNuevo = view.findViewById<EditText>(R.id.editTextFechaSiembraNuevo)
        val btnCrearCultivo = view.findViewById<Button>(R.id.btnCrearCultivo)

        btnCrearCultivo.setOnClickListener {
            val nombre = editTextNombreNuevo.text.toString()
            val descripcion = editTextDescripcionNuevo.text.toString()
            val nivelHumedadOptimo = editTextNivelHumedadOptimoNuevo.text.toString().toIntOrNull() ?: 0
            val tipoPlantas = editTextTipoDePlantasNuevo.text.toString()
            val fechaSiembra = editTextFechaSiembraNuevo.text.toString()

            val nuevoCultivo = Cultivo(
                id = 0,
                nombre = nombre,
                descripcion = descripcion,
                nivel_humedad_optimo = nivelHumedadOptimo,
                tipo_plantas = tipoPlantas,
                fecha_siembra = fechaSiembra
                // Inicializa otros campos según tus necesidades
            )

            cultivoService.crearCultivo(nuevoCultivo).enqueue(object : Callback<Cultivo> {
                override fun onResponse(call: Call<Cultivo>, response: Response<Cultivo>) {
                    if (response.isSuccessful) {
                        val cultivoCreado = response.body()
                        // Actualizar la interfaz con el cultivo creado si es necesario
                    } else {
                        // Manejar errores de la API
                    }
                }

                override fun onFailure(call: Call<Cultivo>, t: Throwable) {
                    // Manejar errores de la conexión
                }
            })
        }

        val editTextIDEditar = view.findViewById<EditText>(R.id.editTextIDEditar)
        val editTextNombreEditar = view.findViewById<EditText>(R.id.editTextNombreEditar)
        val editTextDescripcionEditar = view.findViewById<EditText>(R.id.editTextDescripcionEditar)
        val editTextNivelHumedadOptimoEditar = view.findViewById<EditText>(R.id.editTextNivelHumedadOptimoEditar)
        val editTextTipoDePlantasEditar = view.findViewById<EditText>(R.id.editTextTipoDePlantasEditar)
        val editTextFechaSiembraEditar = view.findViewById<EditText>(R.id.editTextFechaSiembraEditar)
        val btnBuscarCultivo = view.findViewById<Button>(R.id.btnBuscarCultivo)
        val btnActualizarCultivo = view.findViewById<Button>(R.id.btnActualizarCultivo)
        val editTextIDEliminar = view.findViewById<EditText>(R.id.editTextIDEliminar)
        val btnEliminarCultivo = view.findViewById<Button>(R.id.btnEliminarCultivo)

        btnBuscarCultivo.setOnClickListener {
            val id = editTextIDEditar.text.toString().toIntOrNull() ?: 0

            cultivoService.obtenerCultivoPorId(id).enqueue(object : Callback<Cultivo> {
                override fun onResponse(call: Call<Cultivo>, response: Response<Cultivo>) {
                    if (response.isSuccessful) {
                        val cultivoEncontrado = response.body()
                        cultivoEncontrado?.let {
                            editTextNombreEditar.setText(it.nombre)
                            editTextDescripcionEditar.setText(it.descripcion)
                            editTextNivelHumedadOptimoEditar.setText(it.nivel_humedad_optimo.toString())
                            editTextTipoDePlantasEditar.setText(it.tipo_plantas)
                            editTextFechaSiembraEditar.setText(it.fecha_siembra)
                            // Actualizar la interfaz con los datos del cultivo encontrado
                        }
                    } else {
                        // Manejar errores de la API
                    }
                }

                override fun onFailure(call: Call<Cultivo>, t: Throwable) {
                    // Manejar errores de la conexión
                }
            })
        }

        btnActualizarCultivo.setOnClickListener {
            val id = editTextIDEditar.text.toString().toIntOrNull() ?: 0
            val nombre = editTextNombreEditar.text.toString()
            val descripcion = editTextDescripcionEditar.text.toString()
            val nivelHumedadOptimo = editTextNivelHumedadOptimoEditar.text.toString().toIntOrNull() ?: 0
            val tipoPlantas = editTextTipoDePlantasEditar.text.toString()
            val fechaSiembra = editTextFechaSiembraEditar.text.toString()

            val cultivoActualizado = Cultivo(
                id = id,
                nombre = nombre,
                descripcion = descripcion,
                nivel_humedad_optimo = nivelHumedadOptimo,
                tipo_plantas = tipoPlantas,
                fecha_siembra = fechaSiembra
                // Otros campos según tu modelo
            )

            cultivoService.actualizarCultivo(id, cultivoActualizado).enqueue(object : Callback<Cultivo> {
                override fun onResponse(call: Call<Cultivo>, response: Response<Cultivo>) {
                    if (response.isSuccessful) {
                        val cultivoActualizado = response.body()
                        // Actualizar la interfaz con el cultivo actualizado si es necesario
                    } else {
                        // Manejar errores de la API
                    }
                }

                override fun onFailure(call: Call<Cultivo>, t: Throwable) {
                    // Manejar errores de la conexión
                }
            })
        }

        btnEliminarCultivo.setOnClickListener {
            val id = editTextIDEliminar.text.toString().toIntOrNull() ?: 0

            cultivoService.eliminarCultivo(id).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Cultivo eliminado exitosamente
                    } else {
                        // Manejar errores de la API
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Manejar errores de la conexión
                }
            })
        }

        // Obtén la referencia al RecyclerView
        val recyclerViewCultivos = view.findViewById<RecyclerView>(R.id.recyclerViewCultivos)
        recyclerViewCultivos.layoutManager = LinearLayoutManager(requireContext()) // Puedes elegir otro LayoutManager si lo deseas


        return view
    }
}
