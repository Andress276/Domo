package com.example.oficialapp.activity

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
import com.example.oficialapp.R
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.data.RetrofitClientt
import com.example.oficialapp.response.LoginResponse
import com.example.oficialapp.response.User
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

    private fun saveUsernameToSharedPreferences(username: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("YOUR_PREFS_NAME", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("USERNAME_KEY", username)
        editor.apply()
        Log.d("USERNAME_SAVED", "Username guardado: $username")
    }

    fun handleLoginResponse(loginResponse: LoginResponse?) {
        if (loginResponse != null) {
            val token = loginResponse.token // Obtiene el token de LoginResponse
            saveTokenToSharedPreferences(token) // Guarda el token en SharedPreferences

            val user: User = loginResponse.user // Obtiene el objeto User de LoginResponse
            val username = user.name // Obtiene el nombre de usuario del objeto User
            Log.d("USERNAME_DEBUG", "Nombre de usuario obtenido: $username")

            if(username.isNotEmpty()){
                saveUsernameToSharedPreferences(username) // Guarda el nombre de usuario en SharedPreference
            }else{
                Log.e("USERNAME_ERROR", "El nombre de usuario está vacío o nulo.")
            }
        } else {

            }

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

                    if (loginResponse != null) {
                        val token = loginResponse.token
                        saveTokenToSharedPreferences(token)
                        //guardar el token en SharedPreferences


                        //iniciar la siguiente actividad
                        val intent = Intent(this@MainActivity, CasaActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    if (response.code() == 401) {
                        //indica un inicio de sesión fallido
                        Toast.makeText(
                            this@MainActivity,
                            "Credenciales inválidas",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Inicio de sesión fallido",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red", Toast.LENGTH_SHORT).show()
            }
        })


    }
}
