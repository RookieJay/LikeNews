package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import pers.ll.likenews.R
import pers.ll.likenews.model.News

import java.util.ArrayList

class HotNewsAdapter(newsList: ArrayList<News>) : RecyclerView.Adapter<HotNewsAdapter.HotNewsViewHolder>() {

    private var newsList = ArrayList<News>()

    init {
        this.newsList = newsList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HotNewsViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_hot_news, viewGroup, false)
        return HotNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotNewsViewHolder, i: Int) {
        val news = newsList[i]
        holder.ivNews.setImageResource(R.drawable.ic_drawable_icon_signal_fill)
        holder.ivNews.visibility = View.VISIBLE
        holder.tvTitle.text = news.title
        holder.tvSource.text = news.source.toString()
        holder.reviewCount.text = news.comment_count.toString()
        holder.tvNewsTime.text = news.publish_time.toString()
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun replaceAll(newsList: ArrayList<News>) {
        this.newsList = newsList
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


}
