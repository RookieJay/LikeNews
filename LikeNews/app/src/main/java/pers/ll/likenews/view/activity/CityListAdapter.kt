package pers.ll.likenews.view.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.model.City

class CityListAdapter(cities : ArrayList<City>, onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityListHolder>() {

    private var mData = cities
    private var mOnItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CityListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_search, parent, false)
        return CityListHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: CityListHolder, position: Int) {
        val city = mData[position]
        holder.tvCity.text = city.countyname
        holder.itemView.setOnClickListener {
            mOnItemClickListener.onItemClick(city)
        }
    }

    fun replaceAll(list: ArrayList<City>) {
        mData = list

    }


    class CityListHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvCity = itemView.findViewById<TextView>(R.id.tvCity)
    }

    interface OnItemClickListener{
        fun onItemClick(city: City)
    }
}