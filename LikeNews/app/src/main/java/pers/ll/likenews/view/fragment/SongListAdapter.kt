package pers.ll.likenews.view.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wuyr.litepager.LitePager
import pers.ll.likenews.R
import pers.ll.likenews.base.AbsRecyclerAdapter
import pers.ll.likenews.base.RecyclerViewHolderHelper
import pers.ll.likenews.model.SongList
import pers.ll.likenews.utils.ImageUtil
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.UIUtils
import pers.ll.likenews.view.fragment.viewHolder.SongListHeaderHolder

class SongListAdapter(context: Context) : AbsRecyclerAdapter<SongList>(context) {

    private val VIEW_TYPE_HEADER = 0
    private val VIEW_TYPE_CONTENT= 1
    private var firstThreeData = ArrayList<SongList>()

    override fun genItemView(parent: ViewGroup?, viewType: Int): View {
        return LayoutInflater.from(mContext).inflate(R.layout.item_song_list, parent, false)
    }

    override fun onBindDataToViewHolder(holder: RecyclerViewHolderHelper, data: SongList, position: Int) {
        val ivCover = holder.itemView.findViewById<ImageView>(R.id.ivSongList)
        Glide.with(mContext).load(data.coverImgUrl).into(ivCover)
        holder.setText(R.id.tvSongListName, data.name)
        holder.setOnClickListener(R.id.ll_song_list_item, View.OnClickListener {

        })
    }

    override fun addAll(elem: MutableList<SongList>) {
        super.addAll(elem)
        firstThreeData.addAll(elem.subList(0, 3))
    }
}