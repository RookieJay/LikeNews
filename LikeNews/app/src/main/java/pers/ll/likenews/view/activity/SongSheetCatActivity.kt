package pers.ll.likenews.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_song_sheet_cat.*
import pers.ll.likenews.R

class SongSheetCatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_sheet_cat)
        initView()
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(applicationContext, 4)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return 5
            }

        }
        recyclerView.layoutManager = layoutManager
    }
}
