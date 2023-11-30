package com.example.oficialapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.R
import com.example.oficialapp.response.SensorData

class SensorDataAdapter(private val sensorDataList: List<SensorData>) :
    RecyclerView.Adapter<SensorDataAdapter.SensorDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor_data, parent, false)
        return SensorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorDataViewHolder, position: Int) {
        val currentData = sensorDataList[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return sensorDataList.size
    }

    inner class SensorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        private val humedadTextView: TextView = itemView.findViewById(R.id.humedadTextView)
        private val temperaturaTextView: TextView = itemView.findViewById(R.id.temperaturaTextView)

        fun bind(sensorData: SensorData) {
            idTextView.text = "ID: ${sensorData.id}"
            humedadTextView.text = "Humedad: ${sensorData.humedad}"
            temperaturaTextView.text = "Temperatura: ${sensorData.temperatura}"
            // Aquí puedes configurar otros campos según tus necesidades
        }
    }
}
