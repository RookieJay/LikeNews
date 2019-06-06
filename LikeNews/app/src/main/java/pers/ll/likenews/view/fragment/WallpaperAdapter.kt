package pers.ll.likenews.view.fragment

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import pers.ll.likenews.R
import pers.ll.likenews.base.AbsRecyclerAdapter
import pers.ll.likenews.base.RecyclerViewHolderHelper
import pers.ll.likenews.model.Wallpaper

class WallpaperAdapter(context : Context) : AbsRecyclerAdapter<Wallpaper>(context) {

    override fun genItemView(parent: ViewGroup?, viewType: Int): View {
        return LayoutInflater.from(mContext).inflate(R.layout.item_wallpaper, parent, false)
    }

    override fun onBindDataToViewHolder(holder: RecyclerViewHolderHelper, data: Wallpaper, position: Int) {
        Glide.with(mContext).load(data.img_1280_1024).placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_place_holder_black_24dp)).into(holder.itemView.findViewById(R.id.ivWallpaper))
    }

}