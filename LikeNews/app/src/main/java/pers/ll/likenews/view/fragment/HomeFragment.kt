package pers.ll.likenews.view.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.base.MyApplication
import pers.ll.likenews.ui.ChildViewPager

class HomeFragment : BaseFragment() {

    private lateinit var titles : Array<String>
    private lateinit var fragments : ArrayList<Fragment>
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ChildViewPager
    private lateinit var adapter : HotNewsPagerAdapter

    override fun setContentView(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initToolBar()
        initViewData()
        initViewPager()
        initTab()
    }

    private fun initToolBar() {
        val tvTitle = findViewById(R.id.barTitle) as TextView
        val ivLeft = findViewById(R.id.barLeft) as ImageView
        tvTitle.text = "我的首页"
        ivLeft.setImageResource(R.drawable.ic_menu)
        ivLeft.setBackgroundColor(ContextCompat.getColor(mRootView!!.context, R.color.white))
        ivLeft.visibility = View.VISIBLE
    }

    private fun initViewData() {
        titles = arrayOf("新闻", "音乐", "电影")
        fragments = ArrayList()
        fragments.add(HotNewsFragment())
        fragments.add(MusicListFragment())
        fragments.add(HotMovieFragment())
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.viewPager) as ChildViewPager
        viewPager.offscreenPageLimit = 3
        adapter = HotNewsPagerAdapter(childFragmentManager, titles)
        adapter.setData(fragments)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
            }
        })

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
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun loadData() {

    }
}
