package com.example.oficialapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class CompartirFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compartir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botón para compartir la aplicación
        val btnCompartir = view.findViewById<Button>(R.id.btnCompartir)
        btnCompartir.setOnClickListener {
            val appPackageName = requireContext().packageName
            val mensaje = "¡Descubre nuestra increíble app! Descarga la app desde:\n" +
                    "https://play.google.com/store/apps/details?id=$appPackageName\n\n" +
                    "Síguenos en nuestras redes sociales:\n" +
                    "Twitter: @tucuenta\n" +
                    "Facebook: facebook.com/tupagina\n\n" +
                    "Usa el hashtag #TuApp para compartir en redes sociales."

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, mensaje)

            val chooser = Intent.createChooser(intent, "Compartir esta app a través de:")
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(chooser)
            }
        }
    }
}
