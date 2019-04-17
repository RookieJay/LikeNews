package pers.ll.likenews.view.fragment

import android.widget.TextView
import android.widget.Toast
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment

class NewsFragment : BaseFragment() {

    override fun initLayoutId(): Int {
        return R.layout.fragment_home
    }


    override fun loadData() {
        print("NewsFragment启动懒加载")
        val tv = findViewById(R.id.txt)
        if (tv is TextView) {
            tv.text = "这里是立刻新闻"
        }
        Toast.makeText(context, "NewsFragment启动懒加载", Toast.LENGTH_SHORT).show()
    }
}
