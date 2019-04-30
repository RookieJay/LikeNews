package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide

import pers.ll.likenews.R
import pers.ll.likenews.model.News
import pers.ll.likenews.utils.TimeUtils
import java.util.*


class HotNewsAdapter(newsList: ArrayList<News>) : RecyclerView.Adapter<HotNewsAdapter.HotNewsViewHolder>() {

    private var newsList = ArrayList<News>()
    private lateinit var itemClickCallBack : ItemClickCallBack

    init {
        this.newsList = newsList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HotNewsViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_hot_news, viewGroup, false)
        return HotNewsViewHolder(view)
    }

    fun setOnclickListener(itemClickCallBack: ItemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack
    }

    override fun onBindViewHolder(holder: HotNewsViewHolder, i: Int) {
        val news = newsList[i]
        holder.ivNews.setImageResource(R.mipmap.ic_launcher)
        holder.ivNews.visibility = View.VISIBLE
        holder.tvTitle.text = news.title
        holder.tvSource.text = news.source.toString()
        holder.reviewCount.text = String.format("%s评论", news.comment_count.toString())
        val friendTime : String = TimeUtils.getFriendlyTimeSpanByNow(TimeUtils.timestampToString(news.publish_time))
        holder.tvNewsTime.text = friendTime
        if (news.middle_image != null) {
            //动态改变图片大小
//            val params : RelativeLayout.LayoutParams =  RelativeLayout.LayoutParams(
//                news.middle_image!!.width,
//                news.middle_image!!.height) //分别为添加图片的大小
//            holder.ivNews.layoutParams = params
            Glide.with(holder.itemView.context).load(news.middle_image!!.url).into(holder.ivNews)
        }
        holder.itemView.setOnClickListener {
            itemClickCallBack.onclick(news)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun replaceAll(newsList: ArrayList<News>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    inner class HotNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rlNews: RelativeLayout = itemView.findViewById(R.id.rlNews)
        val ivNews: ImageView = itemView.findViewById(R.id.ivNewsImg)
        val tvTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        val tvSource: TextView = itemView.findViewById(R.id.tvNewsSource)
        val reviewCount: TextView = itemView.findViewById(R.id.tvReviewCount)
        val tvNewsTime: TextView = itemView.findViewById(R.id.tvNewsTime)

    }

    interface ItemClickCallBack {
        fun onclick(news: News)
    }


}
