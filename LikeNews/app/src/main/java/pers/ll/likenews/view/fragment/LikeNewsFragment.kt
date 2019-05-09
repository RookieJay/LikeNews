package pers.ll.likenews.view.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.TabEntity
import pers.ll.likenews.ui.ChildViewPager

class LikeNewsFragment : BaseFragment() {

    private lateinit var titles : Array<String>
    private lateinit var fragments : ArrayList<Fragment>
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ChildViewPager
    private lateinit var adapter : HotNewsPagerAdapter
    private lateinit var tabs : ArrayList<TabEntity>

    override fun setContentView(): Int {
        return R.layout.fragment_like_news
    }

    override fun initView() {
        initViewData()
        initViewPager()
        initTab()
    }

    private fun initViewData() {
        titles = arrayOf("社会", "娱乐", "科技", "汽车", "运动", "金融", "军事", "问答")
        tabs = ArrayList()
        fragments = ArrayList()
        for ((i, title) in titles.withIndex()) {
            val fragment = NewsFragment()
            val bundle = Bundle()
            bundle.putString(Const.Key.KEY_TYPE, title)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.viewPager) as ChildViewPager
        viewPager.offscreenPageLimit = 3
        adapter = HotNewsPagerAdapter(childFragmentManager, titles)
        adapter.setData(fragments)
        viewPager.adapter = adapter
    }

    private fun initTab() {
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        val viewLine = findViewById(R.id.view_line) as View
        viewLine.visibility = View.VISIBLE
        tabLayout.setSelectedTabIndicator(0)
        val linearLayout = tabLayout.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        linearLayout.dividerDrawable = ContextCompat.getDrawable(mContext, R.drawable.drawable_divider_vertical)
        //此处不用再设置Tab,setupWithViewPager会removeAllTabs(),直接把titles传入adapter，重写getPageTitle即可
        //会自动绑定viewpager和tablayout
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun loadData() {

    }
}