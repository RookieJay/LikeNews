package pers.ll.likenews.view.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.model.MXWhether

class ForecastAdapter(forecast : ArrayList<MXWhether.Forecast>) : RecyclerView.Adapter<ForecastAdapter.ForecastHolder>() {

    private var mData = forecast

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ForecastHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        val whether = mData[position]
        holder.tvDate.text = whether.date
        holder.tvWhether.text = whether.weather
        holder.tvDayTemp.text = String.format("%s°C", whether.temp_day_c)
        holder.tvNightTemp.text = String.format("%s°C", whether.temp_night_c)
    }

    fun replaceAll(forecasts: ArrayList<MXWhether.Forecast>) {
        this.mData = forecasts
    }


    class ForecastHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvDate = itemView.findViewById(R.id.tvDate) as TextView
        val tvWhether = itemView.findViewById(R.id.tvWhether) as TextView
        val tvDayTemp = itemView.findViewById(R.id.tvDayTemp) as TextView
        val tvNightTemp = itemView.findViewById(R.id.tvNightTemp) as TextView
    }
}