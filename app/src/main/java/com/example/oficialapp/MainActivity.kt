package com.example.oficialapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.data.RetrofitClient
import com.example.oficialapp.data.RetrofitClientt
import com.example.oficialapp.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazar vistas con variables
        editTextEmail = findViewById(R.id.editTextEmaill)
        editTextPassword = findViewById(R.id.editTextPasswordd)
        buttonLogin = findViewById(R.id.buttonLogin)

        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (isValidCredentials(email, password)) {
                performLogin(email, password)
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTokenToSharedPreferences(token: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("YOUR_PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("TOKEN_KEY", token)
        editor.apply()
        Log.d("TOKEN_SAVED", "Token guardado: $token")
    }



    private fun isValidCredentials(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun performLogin(email: String, password: String) {
        val apiService = RetrofitClientt.instance.create(ApiService::class.java)

        val credentials = HashMap<String, String>()
        credentials["email"] = email
        credentials["password"] = password

        val call = apiService.login(credentials)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    // Maneja la respuesta del servidor después del inicio de sesión exitoso
                    if (loginResponse != null) {
                        val token = loginResponse.token
                        saveTokenToSharedPreferences(token)
                        // Aquí puedes guardar el token en SharedPreferences o utilizarlo según necesites
                        // Por ejemplo, iniciar la siguiente actividad


                        val intent = Intent(this@MainActivity, CasaActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
