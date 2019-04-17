package pers.ll.likenews.view.fragment

import android.widget.TextView
import android.widget.Toast
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment

class PersonalFragment : BaseFragment() {

    override fun initLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadData() {
        print("PersonalFragment启动懒加载")
        val tv = findViewById(R.id.txt)
        if (tv is TextView) {
            tv.text = "这里是个人中心"
        }
        Toast.makeText(context, "PersonalFragment启动懒加载", Toast.LENGTH_SHORT).show()
    }
}
