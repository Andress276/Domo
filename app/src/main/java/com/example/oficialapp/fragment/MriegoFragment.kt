package com.example.oficialapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.oficialapp.R
import okhttp3.*
import java.io.IOException

class MriegoFragment : Fragment() {

    private lateinit var toggleButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mriego, container, false)

        toggleButton = view.findViewById(R.id.toggleButton)

        toggleButton.setOnClickListener {
            val relayState = determineRelayState()
            toggleRelay(relayState)
        }

        return view
    }

    private fun determineRelayState(): Boolean {
        // Aquí puedes implementar la lógica para determinar el estado actual del relé
        // Por ejemplo, si tienes una variable booleana que almacena el estado del relé, puedes retornarla aquí.
        return true // Cambia esto según tu lógica real
    }

    private fun toggleRelay(relayState: Boolean) {
        val action = if (relayState) "on" else "off"
        val url = "http://192.168.137.71/$action"

        Log.d("MriegoFragment", "URL de solicitud: $url") // Imprime la URL que se está utilizando

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(RequestBody.create(null, ""))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MriegoFragment", "Error al realizar la solicitud: ${e.message}") // Imprime el error de la solicitud
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("MriegoFragment", "Solicitud exitosa") // Indica que la solicitud fue exitosa
                    // Manejar la respuesta del servidor si es necesario
                } else {
                    Log.e("MriegoFragment", "La solicitud no fue exitosa: ${response.code}") // Imprime el código de respuesta
                }
                response.close()
            }
        })
    }
}