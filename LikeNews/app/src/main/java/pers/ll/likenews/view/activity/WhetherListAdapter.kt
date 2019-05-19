package pers.ll.likenews.view.activity

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.MXWhether
import pers.ll.likenews.utils.TimeUtils

class WhetherListAdapter(whethers : ArrayList<MXWhether>) : RecyclerView.Adapter<WhetherListAdapter.WhetherHolder>() {

    private var mData = whethers

    override fun onCreateViewHolder(parent : ViewGroup, position: Int): WhetherHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_whether, parent, false)
        return WhetherHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: WhetherHolder, position: Int) {
        val whether = mData[position]
        val date = TimeUtils.string2Date(whether.weatherDetailsInfo.publishTime)
        holder.tvDate.text = TimeUtils.date2String(date, Const.DateFormat.CN_M_D)
        holder.tvLocation.text = whether.city
        holder.tvRealTime.text = whether.realtime.time
        holder.tvTempDuration.text = String.format("%s°/%s°", whether.weathers[0].temp_night_c, whether.weathers[0].temp_day_c)
        holder.tvWeekDay.text = whether.weathers[0].week
        holder.tvWhether.text = whether.weathers[0].weather
        val imgName = "w_icon_$whether.weathers[0].img"
        val imgId = holder.itemView.context.resources.getIdentifier(imgName, "drawable", "pers.ll.likenews.view.activity")
        val drawable = ContextCompat.getDrawable(holder.itemView.context, imgId)
        Glide.with(holder.ivWhether).load(drawable).into(holder.ivWhether)
    }

    fun replaceAll(list: ArrayList<MXWhether>) {
        mData = list
        notifyDataSetChanged()
    }


    class WhetherHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val tvDate = itemView.findViewById(R.id.tvDate) as TextView
        val tvWeekDay = itemView.findViewById(R.id.tvWeekDay) as TextView
        val tvLocation = itemView.findViewById(R.id.tvLocation) as TextView
        val tvRealTime = itemView.findViewById(R.id.tvRealTime) as TextView
        val tvWhether = itemView.findViewById(R.id.tvWhether) as TextView
        val tvTempDuration = itemView.findViewById(R.id.tvTempDuration) as TextView
        val ivWhether = itemView.findViewById(R.id.ivWhether) as ImageView
    }
}