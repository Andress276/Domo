package com.example.oficialapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.data.RetrofitClient
import com.example.oficialapp.R
import com.example.oficialapp.data.RetrofitClienttt
import com.example.oficialapp.response.UserProfile
import com.example.oficialapp.databinding.FragmentPerfilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {

    private lateinit var apiService: ApiService
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String
    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RetrofitClienttt.getInstance()
        apiService = retrofit.create(ApiService::class.java)
        sharedPreferences = requireActivity().getSharedPreferences("YOUR_PREFS_NAME", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN_KEY", "") ?: ""

        binding.buttonUpdate.setOnClickListener {
            val newName = binding.editTextNamee.text.toString().trim()
            val newEmail = binding.editTextEmaill.text.toString().trim()
            val newPassword = binding.editTextPasswordd.text.toString().trim()

            val updatedProfile = UserProfile(newName, newEmail, newPassword)

            updateProfile(updatedProfile)
        }

        fetchProfileData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchProfileData() {
        apiService.getProfile("Bearer $token").enqueue(object : Callback<UserProfile> {
            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    userProfile?.let {

                        //guarda el nombre de usurio
                        val editor = sharedPreferences.edit()
                        editor.putString("USER_NAME", it.name)
                        editor.apply()

                        binding.editTextNamee.setText(it.name)
                        binding.editTextEmaill.setText(it.email)
                        binding.editTextPasswordd.setText(it.password)
                    }
                } else {
                    // Manejar errores al obtener el perfil
                }
            }

            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                // Manejar errores de conexión o red
            }
        })
    }

    private fun updateProfile(updatedProfile: UserProfile) {
        Log.d("PerfilFragment", "Iniciando actualización del perfil")
        apiService.updateProfile("Bearer $token", updatedProfile)
            .enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.isSuccessful) {
                        Log.d("PerfilFragment", "Actualización exitosa del perfil")
                        activity?.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Perfil actualizado exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        // Manejar errores al actualizar el perfil
                        Log.d("PerfilFragment", "Error al actualizar el perfil: ${response.code()}")
                        activity?.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Error al actualizar el perfil",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    // Manejar errores de conexión o red al actualizar el perfil
                    Log.e("PerfilFragment", "Error de conexión al actualizar el perfil: ${t.message}")
                }
            })
    }
}
