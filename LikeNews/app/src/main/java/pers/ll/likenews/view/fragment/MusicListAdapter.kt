package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.model.Music

class MusicListAdapter(musicList: ArrayList<Music>) : RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    private var data = musicList

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MusicViewHolder {
        val itemView : View = LayoutInflater.from(p0.context).inflate(R.layout.item_music_list, p0, false)
        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, i: Int) {
        val music : Music = data[i]
        holder.tvMusicName.text = music.name
    }

    class MusicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvMusicName : TextView = itemView.findViewById(R.id.tvMusicName)
    }
}