package pers.ll.likenews.view.fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.include_base_toolbar.*
import kotlinx.android.synthetic.main.item_hot_news.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music

class MusicPlayActivity : AppCompatActivity() {

    private lateinit var music: Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_play)
        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        initView()
    }

    private fun initView() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        barTitle.text = music.name
        if (music.pic != null) {
//            Glide.with(applicationContext).load(music.pic).into(ivNewsImg)

        }

    }
}
