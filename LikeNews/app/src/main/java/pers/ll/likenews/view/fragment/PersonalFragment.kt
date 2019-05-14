package pers.ll.likenews.view.fragment

import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.ui.MyTextView

class PersonalFragment : BaseFragment() {

    private lateinit var tvTitle : MyTextView

    override fun setContentView(): Int {
        return R.layout.fragment_personal
    }

    override fun initView() {
        tvTitle = findViewById(R.id.tvTitle) as MyTextView
        tvTitle.text = resources.getString(R.string.txt_poetry)
    }

    override fun loadData() {

    }
}
