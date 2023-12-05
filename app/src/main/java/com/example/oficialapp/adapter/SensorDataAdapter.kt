package com.example.oficialapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oficialapp.R
import com.example.oficialapp.response.SensorData

class SensorDataAdapter : RecyclerView.Adapter<SensorDataAdapter.SensorDataViewHolder>() {

    private var sensorDataList: List<SensorData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor_data, parent, false)
        return SensorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorDataViewHolder, position: Int) {
        val currentItem = sensorDataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return sensorDataList.size
    }

    fun setData(newList: List<SensorData>) {
        sensorDataList = newList
        notifyDataSetChanged()
    }

    inner class SensorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.textViewID)
        private val humedadTextView: TextView = itemView.findViewById(R.id.textViewHumedad)
        private val temperaturaTextView: TextView = itemView.findViewById(R.id.textViewTemperatura)
        private val humedadSueloTextView: TextView = itemView.findViewById(R.id.textViewHumedadSuelo)
        private val flujoAguaTextView: TextView = itemView.findViewById(R.id.textViewFlujoAgua)
        //private val fechaHoraTextView: TextView = itemView.findViewById(R.id.textViewFechaHora)
        private val createdAtTextView: TextView = itemView.findViewById(R.id.textViewCreatedAt)
        //private val updatedAtTextView: TextView = itemView.findViewById(R.id.textViewUpdatedAt)

        fun bind(sensorData: SensorData) {
            idTextView.text = "Dato #: ${sensorData.id}"
            humedadTextView.text = "Humedad: ${sensorData.humedad}%"
            temperaturaTextView.text = "Temperatura: ${sensorData.temperatura}°C"
            humedadSueloTextView.text = "Humedad de Suelo: ${sensorData.humedad_suelo}"
            flujoAguaTextView.text = "Flujo de Agua: ${sensorData.flujo_agua}"
            //fechaHoraTextView.text = "Fecha y Hora: ${sensorData.fecha_hora}"
            createdAtTextView.text = "Fecha y hora: ${sensorData.created_at}"
            //updatedAtTextView.text = "Actualizado en: ${sensorData.updated_at}"
        }
    }
}
