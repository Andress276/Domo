package com.example.oficialapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.R
import com.example.oficialapp.response.Cultivo

class CultivoAdapter : RecyclerView.Adapter<CultivoAdapter.CultivoViewHolder>() {
    private var cultivosList: List<Cultivo> = emptyList()

    fun actualizarCultivos(cultivos: List<Cultivo>) {
        cultivosList = cultivos
        notifyDataSetChanged()
    }

    inner class CultivoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionTextView)
        private val nivelHumedadTextView: TextView = itemView.findViewById(R.id.nivelHumedadTextView)
        private val tipoPlantasTextView: TextView = itemView.findViewById(R.id.tipoPlantasTextView)
        private val fechaSiembraTextView: TextView = itemView.findViewById(R.id.fechaSiembraTextView)

        fun bind(cultivo: Cultivo) {
            nombreTextView.text = cultivo.nombre
            descripcionTextView.text = cultivo.descripcion
            nivelHumedadTextView.text = cultivo.nivel_humedad_optimo.toString()
            tipoPlantasTextView.text = cultivo.tipo_plantas
            fechaSiembraTextView.text = cultivo.fecha_siembra
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CultivoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cultivo, parent, false)
        return CultivoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CultivoViewHolder, position: Int) {
        val cultivo = cultivosList[position]
        holder.bind(cultivo)
    }

    override fun getItemCount(): Int {
        return cultivosList.size
    }
}
