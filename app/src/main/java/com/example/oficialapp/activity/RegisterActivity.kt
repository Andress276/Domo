package com.example.oficialapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oficialapp.R
import com.example.oficialapp.data.RetrofitClient
import com.example.oficialapp.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextName = findViewById(R.id.editTextNamee)
        editTextEmail = findViewById(R.id.editTextEmaill)
        editTextPassword = findViewById(R.id.editTextPasswordd)
        buttonRegister = findViewById(R.id.buttonregister)

        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad
        }

        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Validar que los campos no estén vacíos
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Crear un HashMap con los datos del usuario
                val userData = HashMap<String, String>()
                userData["name"] = name
                userData["email"] = email
                userData["password"] = password

                // Enviar los datos al servidor para el registro
                // Aquí debes utilizar Retrofit u otra biblioteca para realizar la solicitud HTTP al servidor
                // Ejemplo de cómo se haría con Retrofit:
                RetrofitClient.instance.registerUser(userData)
                    .enqueue(object : Callback<UserResponse> {
                        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                            if (response.isSuccessful) {
                                val userResponse = response.body()
                                // Registro exitoso, puedes realizar acciones aquí, por ejemplo, mostrar un mensaje
                                Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish() // Finalizar la actividad actual

                            } else {
                                // Manejar errores en la respuesta del servidor
                                Toast.makeText(this@RegisterActivity, "Error en el registro", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            // Manejar errores de conexión u otros errores
                            Toast.makeText(this@RegisterActivity, "Error en el registro", Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {
                // Mostrar un mensaje indicando que todos los campos deben estar completos
                // Por ejemplo:
               Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

