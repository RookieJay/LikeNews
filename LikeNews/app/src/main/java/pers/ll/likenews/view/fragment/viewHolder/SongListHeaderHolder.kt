package pers.ll.likenews.view.fragment.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.wuyr.litepager.LitePager
import pers.ll.likenews.R

class SongListHeaderHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val litePager = itemView.findViewById<LitePager>(R.id.litePager)
}