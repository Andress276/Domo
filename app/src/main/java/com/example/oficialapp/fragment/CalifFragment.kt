package com.example.oficialapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oficialapp.R

class CalifFragment : Fragment() {

    private lateinit var ratingBar: RatingBar
    private lateinit var editTextComment: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calif, container, false)

        ratingBar = view.findViewById(R.id.ratingBar)
        editTextComment = view.findViewById(R.id.editTextComment)
        val enviarButton: Button = view.findViewById(R.id.buttonEnviar)

        enviarButton.setOnClickListener {
            val rating = ratingBar.rating
            val comment = editTextComment.text.toString()

            // Aquí puedes realizar la acción con la calificación y el comentario
            // por ejemplo, enviarlos a una base de datos o mostrar un mensaje de agradecimiento.

            Toast.makeText(requireContext(), "¡Gracias por tu calificación!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
