package com.example.oficialapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.oficialapp.R

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el nombre de usuario almacenado en SharedPreferences
        val userName = requireActivity().getSharedPreferences("YOUR_PREFS_NAME", Context.MODE_PRIVATE)
            .getString("USER_NAME", "")

        // Buscar el TextView en tu diseño XML por su ID
        val welcomeTextView: TextView = view.findViewById(R.id.textViewWelcomeHome)

        // Crear el mensaje de bienvenida con el nombre de usuario
        val welcomeMessage = "¡Bienvenido, $userName!"

        // Establecer el mensaje de bienvenida en el TextView
        welcomeTextView.text = welcomeMessage
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    }
