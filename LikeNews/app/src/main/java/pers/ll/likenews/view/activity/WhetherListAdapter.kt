package pers.ll.likenews.view.activity

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.base.MyApplication
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.City
import pers.ll.likenews.model.MXWhether
import pers.ll.likenews.utils.TimeUtils

class WhetherListAdapter(onItemClickListener: OnItemClickListener, whethers : ArrayList<MXWhether>) : RecyclerView.Adapter<WhetherListAdapter.WhetherHolder>() {

    private var mData = whethers
    private var mOnItemClickListener = onItemClickListener

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
        val publishDate = TimeUtils.string2Date(whether.weatherDetailsInfo.publishTime)
        holder.tvRealTime.text = TimeUtils.date2String(publishDate, Const.DateFormat.HHMM)
        holder.tvTempDuration.text = String.format("%s°/%s°", whether.weathers[0].temp_night_c, whether.weathers[0].temp_day_c)
        holder.tvWeekDay.text = whether.weathers[0].week
        holder.tvWhether.text = whether.weathers[0].weather
        val imgName = "w_icon_"+whether.weathers[0].img+"_big"
        val imgId = MyApplication.getInstance().resources.getIdentifier(imgName, "drawable", holder.itemView.context.packageName)
        Log.d("packageName", holder.itemView.context.packageName)
        Log.d("img的id", "$imgName $imgId")
        val drawable = ContextCompat.getDrawable(holder.itemView.context, imgId)
        Glide.with(holder.ivWhether).load(drawable).into(holder.ivWhether)
//        holder.ivWhether.setImageResource(imgId)
        holder.itemView.setOnClickListener{
            mOnItemClickListener.onItemClick(whether)
        }
    }

    fun replaceAll(list: ArrayList<MXWhether>) {
        mData = list
    }

    fun addData(whether: MXWhether) {
        if (mData.size > 0) {
            for (data in mData) {
                if (whether == data) {
                    return
                }
            }
        }
        mData.add(whether)
    }

    fun isExist(city: City): Boolean {
        val cityNames = ArrayList<String>()
        return if (mData.size > 0) {
            for (data in mData) {
                cityNames.add(data.city)
            }
            cityNames.contains(city.countyname)
        } else {
            false
        }
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

    interface OnItemClickListener {
        fun onItemClick(whether: MXWhether)
    }
}