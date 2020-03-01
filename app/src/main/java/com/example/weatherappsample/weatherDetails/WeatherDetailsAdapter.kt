package com.example.weatherappsample.weatherDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappsample.R
import com.example.weatherappsample.Util.CommonUtils
import com.example.weatherappsample.data.ForeCastDetail
import com.example.weatherappsample.data.WeatherForeCast

class WeatherDetailsAdapter(private val context : Context,
private val list : ArrayList<ForeCastDetail>) : RecyclerView.Adapter<WeatherDetailsAdapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.row_weather_forecast_details , parent , false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.tvDay.text = list.get(position).dtTxt
        holder.tvTemp.text = CommonUtils.convertKelvinToCelsius(list.get(position).main?.temp)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvDay = itemView.findViewById<TextView>(R.id.tv_day)
        val tvTemp = itemView.findViewById<TextView>(R.id.tv_temp)
    }

}