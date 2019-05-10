package pers.ll.likenews.view.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pers.ll.likenews.R
import pers.ll.likenews.model.SongSheetCategory
import java.util.zip.Inflater

class SongSheeCatAdapter(categories: ArrayList<SongSheetCategory>) : RecyclerView.Adapter<SubCatViewHolder>() {

    private var mData = categories

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SubCatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song_sheet_sub_cat, parent, false)
        return SubCatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: SubCatViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}