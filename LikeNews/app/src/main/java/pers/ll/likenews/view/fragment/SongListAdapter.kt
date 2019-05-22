package pers.ll.likenews.view.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.base.AbsRecyclerAdapter
import pers.ll.likenews.base.RecyclerViewHolderHelper
import pers.ll.likenews.model.SongList

class SongListAdapter(context: Context) : AbsRecyclerAdapter<SongList>(context) {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_CONTENT= 1
    private var firstThreeData = ArrayList<SongList>()

    override fun genItemView(parent: ViewGroup?, viewType: Int): View {
        return if (viewType == VIEW_TYPE_HEADER) {
            LayoutInflater.from(mContext).inflate(R.layout.item_song_list_header, parent, false)
        } else {
            LayoutInflater.from(mContext).inflate(R.layout.item_song_list, parent, false)
        }

    }

    override fun onBindDataToViewHolder(holder: RecyclerViewHolderHelper, data: SongList, position: Int) {
        when(getViewType(data)) {
            VIEW_TYPE_HEADER -> {

            }
            VIEW_TYPE_CONTENT -> {
                val ivCover = holder.itemView.findViewById<ImageView>(R.id.ivSongList)
                Glide.with(mContext).load(data.coverImgUrl).into(ivCover)
                holder.setText(R.id.tvSongListName, data.name)
            }
        }

    }

    override fun getViewType(data: SongList?): Int {
        if (firstThreeData.contains(data)) {
            return VIEW_TYPE_HEADER
        } else {
            return VIEW_TYPE_CONTENT
        }

        return super.getViewType(data)
    }

    override fun addAll(elem: MutableList<SongList>) {
        super.addAll(elem)
        firstThreeData.addAll(elem.subList(0, 2))
    }
}