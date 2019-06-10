package pers.ll.likenews.view.activity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.SystemInfo

class SysInfoAdapter(context: Context, list : ArrayList<SystemInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_TITLE = 0
    private val VIEW_TYPE_CONTENT = 1

    private var mContext = context
    private var mData = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View
        return if (viewType == VIEW_TYPE_TITLE) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sys_info_title, parent, false)
            TitleViewHolder(view)
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sys_info, parent, false)
            ContentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position: Int) {
        val data = mData[position]
        if (holder is TitleViewHolder) {
            val titleHolder: TitleViewHolder = holder as TitleViewHolder
            titleHolder.tvTitle.text = data.title
        } else {
            val contentViewHolder: ContentViewHolder = holder as ContentViewHolder
            contentViewHolder.tvKey.text = data.key
            contentViewHolder.tvValue.text = data.value
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].type == Const.Type.SYS_INFO_TYPE_CONTENT) {
            VIEW_TYPE_CONTENT
        } else {
            VIEW_TYPE_TITLE
        }
    }

    fun refresh(list: ArrayList<SystemInfo>) {
        mData = list
        notifyDataSetChanged()
    }

    class TitleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvSysInfoTitle)
    }

    class ContentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvKey: TextView  = itemView.findViewById(R.id.tvSysInfoKey)
        val tvValue: TextView  = itemView.findViewById(R.id.tvSysInfoValue)
    }

}