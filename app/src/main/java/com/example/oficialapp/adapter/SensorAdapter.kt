package com.example.oficialapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.R
import com.example.oficialapp.response.SensorData

class SensorAdapter(private val sensorDataList: List<SensorData>) :
    RecyclerView.Adapter<SensorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = sensorDataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return sensorDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val idTextView: TextView = itemView.findViewById(R.id.textViewId)
        private val humedadTextView: TextView = itemView.findViewById(R.id.textViewHumedad)
        private val temperaturaTextView: TextView = itemView.findViewById(R.id.textViewTemperatura)
        private val humedadSueloTextView: TextView = itemView.findViewById(R.id.textViewHumedadSuelo)
        private val flujoAguaTextView: TextView = itemView.findViewById(R.id.textViewFlujoAgua)
        //private val fechaHoraTextView: TextView = itemView.findViewById(R.id.textViewFechaHora)
        //private val createdAtTextView: TextView = itemView.findViewById(R.id.textViewCreatedAt)
        //private val updatedAtTextView: TextView = itemView.findViewById(R.id.textViewUpdatedAt)

        fun bind(sensorData: SensorData) {
            //idTextView.text = "ID: ${sensorData.id}"
            humedadTextView.text = "Humedad: ${sensorData.humedad}"
            temperaturaTextView.text = "Temperatura: ${sensorData.temperatura}"
            humedadSueloTextView.text = "Humedad del suelo: ${sensorData.humedad_suelo}"
            flujoAguaTextView.text = "Flujo de agua: ${sensorData.flujo_agua}"
            //fechaHoraTextView.text = "Fecha y hora: ${sensorData.fecha_hora}"
            //createdAtTextView.text = "Creado en: ${sensorData.created_at}"
            //updatedAtTextView.text = "Actualizado en: ${sensorData.updated_at}"
        }
    }
}
