package com.example.oficialapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction

class FuncinlidadFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_funcinlidad, container, false)

        val sensorLayout = view.findViewById<FrameLayout>(R.id.sensor_layout)
        val registrosLayout = view.findViewById<FrameLayout>(R.id.registros_layout)
        val modoRiegoLayout = view.findViewById<FrameLayout>(R.id.modo_riego_layout)
        val cultivosLayout = view.findViewById<FrameLayout>(R.id.cultivos_layout)

        sensorLayout.setOnClickListener {
            navigateToFragment(SensoresFragment())
        }

        registrosLayout.setOnClickListener {
            navigateToFragment(RegistrosFragment())
        }

        modoRiegoLayout.setOnClickListener {
            navigateToFragment(MriegoFragment())
        }

        cultivosLayout.setOnClickListener {
            navigateToFragment(CultivosFragment())
        }

        return view
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
