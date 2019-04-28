package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.model.Music


class MusicListAdapter(musicList: ArrayList<Music>) : RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    private var data = musicList
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MusicViewHolder {
        val itemView : View = LayoutInflater.from(p0.context).inflate(R.layout.item_music_list, p0, false)
        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music : Music = data[position]
        holder.ivPlay.setImageResource(R.drawable.icon_music_play)
        holder.tvMusicName.text = music.name
        if (position < 9) {
            holder.tvMusicNo.text = String.format("%s%s", "0", (position+1).toString())
        } else{
            holder.tvMusicNo.text = (position+1).toString()
        }

        holder.tvSinger.text = music.singer
        holder.itemView.setOnClickListener(View.OnClickListener {
            v -> onItemClickListener.onItemClick(music)
        })
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun replaceAll(musicList: ArrayList<Music>) {
        data = musicList
        notifyDataSetChanged()

    }

    class MusicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvMusicName : TextView = itemView.findViewById(R.id.tvMusicName)
        val tvMusicNo : TextView = itemView.findViewById(R.id.tvMusicNo)
        val tvSinger : TextView = itemView.findViewById(R.id.tvSinger)
        val ivPlay : ImageView = itemView.findViewById(R.id.ivPlay)
    }

    interface OnItemClickListener {
        fun onItemClick(music: Music)
    }
}