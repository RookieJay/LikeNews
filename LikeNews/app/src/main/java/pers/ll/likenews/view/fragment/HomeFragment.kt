package pers.ll.likenews.view.fragment

import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.base.ToastUtils

class HomeFragment : BaseFragment() {

    override fun initLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadData() {
        print("HomeFragment启动懒加载")
        val tv = findViewById(R.id.txt)
        if (tv is TextView) {
            tv.text = "这里是首页"
        }
       ToastUtils.showShort("首页懒启动")
    }
}
