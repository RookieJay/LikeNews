package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.model.Movie
import java.text.FieldPosition

class HotMovieAdapter(movieList : ArrayList<Movie>, onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<HotMovieAdapter.Holder>() {

    private var mData = movieList
    private var mOnItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.item_hot_movie, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: Holder, position : Int) {
        val movie = mData[position]
        Glide.with(holder.ivMovie).load(movie.images.large).into(holder.ivMovie)
        holder.tvMovieName.isSelected = true
        holder.tvMovieName.isFocusable = true
        holder.tvMovieName.isFocusableInTouchMode = true
        holder.tvMovieName.text = movie.title
        val rating = movie.rating.average.toFloat()
        if (rating == 0f) {
            holder.rbRating.visibility = View.GONE
            holder.tvScore.text = "暂无评分"
        } else {
            holder.rbRating.rating = rating / 2
            holder.tvScore.text = movie.rating.average.toString()
        }
        holder.itemView.setOnClickListener{
            mOnItemClickListener.onItemClick(movie)
        }
    }

    fun replaceAll(list: ArrayList<Movie>) {
        this.mData = list
        notifyDataSetChanged()
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivMovie = itemView.findViewById(R.id.ivMovie) as ImageView
        val tvMovieName = itemView.findViewById(R.id.tvMovieName) as TextView
        val rbRating = itemView.findViewById(R.id.rbRating) as RatingBar
        val tvScore = itemView.findViewById(R.id.tvScore) as TextView
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}