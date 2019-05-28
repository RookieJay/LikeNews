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
//        return if (viewType == VIEW_TYPE_HEADER) {
//            LayoutInflater.from(mContext).inflate(R.layout.layout_song_list_header, parent, false)
//        } else {
//            LayoutInflater.from(mContext).inflate(R.layout.item_song_list, parent, false)
//        }
        return LayoutInflater.from(mContext).inflate(R.layout.item_song_list, parent, false)
    }

    override fun onBindDataToViewHolder(holder: RecyclerViewHolderHelper, data: SongList, position: Int) {
//        when(getViewType(data)) {
//            VIEW_TYPE_HEADER -> {
//                val litePager = holder.itemView.findViewById<LitePager>(R.id.litePager)
//                    when(position) {
//                        0 -> {
//                            val imageView1 = litePager.findViewById(R.id.ivHeader1) as ImageView
//                            Glide.with(mContext).load(data.coverImgUrl).into(imageView1)
//                        }
//                        1 -> {
//                            val imageView2 = litePager.findViewById(R.id.ivHeader2) as ImageView
//                            Glide.with(mContext).load(data.coverImgUrl).into(imageView2)
//                        }
//                        2 -> {
//                            val imageView3 = litePager.findViewById(R.id.ivHeader3) as ImageView
//                            Glide.with(mContext).load(data.coverImgUrl).into(imageView3)
//                        }
//                }

//                val imageView = ImageView(mContext)
//                val layoutParams = imageView.layoutParams
//                layoutParams.width = UIUtils.dp2px(mContext, 150f)
//                layoutParams.height = UIUtils.dp2px(mContext, 160f)
//                imageView.layoutParams = layoutParams
//                litePager.addView(imageView)
//                Glide.with(mContext).load(data.coverImgUrl).into(imageView)
                val ivCover = holder.itemView.findViewById<ImageView>(R.id.ivSongList)
                Glide.with(mContext).load(data.coverImgUrl).into(ivCover)
                holder.setText(R.id.tvSongListName, data.name)

    }

//    override fun getViewType(data: SongList?): Int {
//        return if (firstThreeData.size > 0 && firstThreeData.contains(data)) {
//            VIEW_TYPE_HEADER
//        } else {
//            VIEW_TYPE_CONTENT
//        }
//    }

    override fun addAll(elem: MutableList<SongList>) {
        super.addAll(elem)
        firstThreeData.addAll(elem.subList(0, 3))
    }
}