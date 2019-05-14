package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pers.ll.likenews.R
import pers.ll.likenews.ui.MyTextView

class PoetryAdapter(sentences : ArrayList<String>) : RecyclerView.Adapter<PoetryAdapter.PoetryViewHolder>() {

    private var mData = sentences

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PoetryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_poetry, parent, false)
        return PoetryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: PoetryViewHolder, position: Int) {
        holder.tvSentence.text = mData[position]
    }

    fun replaceAll(sentences: ArrayList<String>) {
        mData = sentences
        notifyDataSetChanged()
    }


    inner class PoetryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvSentence = itemView.findViewById(R.id.tvSentence) as MyTextView
    }
}