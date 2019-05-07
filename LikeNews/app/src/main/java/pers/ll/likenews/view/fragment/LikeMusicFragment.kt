package pers.ll.likenews.view.fragment

import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment

class LikeMusicFragment : BaseFragment() {

    private lateinit var tvTest : TextView

    override fun setContentView(): Int {
        return R.layout.fragment_like_news
    }

    override fun initView() {
        tvTest = findViewById(R.id.tvTest) as TextView
        tvTest.text = "立刻音乐"
    }

    override fun loadData() {

    }

}