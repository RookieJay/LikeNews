package pers.ll.likenews.view.fragment

import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment

class PersonalFragment : BaseFragment() {

    private lateinit var tvTest : TextView

    override fun setContentView(): Int {
        return R.layout.item_hot_news
    }

    override fun initView() {
    }

    override fun loadData() {
    }
}
