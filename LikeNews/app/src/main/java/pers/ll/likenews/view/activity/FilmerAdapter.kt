package pers.ll.likenews.view.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.model.Movie

class FilmerAdapter(filmer: ArrayList<Movie.Cast>, onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<FilmerAdapter.FilmerHolder>() {

    private var mData = filmer
    private var mOnItemClickListener = onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FilmerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_filmer, parent, false)
        return FilmerHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: FilmerHolder, position: Int) {
        val filmer = mData[position]
        holder.tvFilmer.text = filmer.name
        Glide.with(holder.ivFilmer).load(filmer.avatars.large).into(holder.ivFilmer)
        holder.tvFilmer.isSelected = true
        holder.tvFilmer.isFocusable = true
        holder.tvFilmer.isFocusableInTouchMode = true
        holder.itemView.setOnClickListener{
            mOnItemClickListener.OnItemClick(filmer)
        }
    }

    fun replaceAll(filmerList: ArrayList<Movie.Cast>) {
        mData = filmerList
        notifyDataSetChanged()
    }


    class FilmerHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivFilmer = itemView.findViewById(R.id.ivFilmer) as ImageView
        val tvFilmer = itemView.findViewById(R.id.tvFilmer) as TextView
    }

    interface OnItemClickListener {
        fun OnItemClick(cast : Movie.Cast)
    }
}