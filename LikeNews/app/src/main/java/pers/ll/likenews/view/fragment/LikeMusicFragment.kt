package pers.ll.likenews.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_like_music.*
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.ui.ChildViewPager
import pers.ll.likenews.ui.NoScrollViewPager
import pers.ll.likenews.view.activity.MainActivity
import pers.ll.likenews.view.activity.SongSheetCatActivity

class LikeMusicFragment : BaseFragment(), MainActivity.MyTouchListener {

    private var mSongSheetListeners = ArrayList<SongSheetTouchListener>()
    private lateinit var childListener : SongSheetTouchListener

    private lateinit var titles : Array<String>
    private lateinit var fragments : ArrayList<Fragment>
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager
    private lateinit var adapter : HotNewsPagerAdapter
    private lateinit var curActivity : MainActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        curActivity = context as MainActivity
    }

    override fun setContentView(): Int {
        return R.layout.fragment_like_music
    }

    override fun initView() {
        initViewData()
        initViewPager()
        initTab()
        setListener()
    }

    private fun initViewData() {
        fragments = ArrayList()
        titles = arrayOf("推荐", "华语", "民谣", "影视原声", "轻音乐", "学习", "工作")

        for ((index, title) in titles.withIndex()) {
            val fragment  = SongSheetListFragment()
            val bundle = Bundle()
            bundle.putInt(Const.Key.KEY_TYPE, index)
            fragment.arguments
            fragments.add(fragment)
        }
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.viewPager) as ViewPager
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

    private fun setListener() {
        //注册触摸事件
        curActivity.registerMyTouchListener(this)
        ivMoreSongList.setOnClickListener {
            startActivity(Intent(context, SongSheetCatActivity().javaClass))
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //不在litePager范围类
//        viewPager.setIsScanScroll(!UIUtils.inRangeOfView(litePager, event))
//        return !UIUtils.inRangeOfView(litePager, event)
        if (mSongSheetListeners.size > 0) {
            for (listener in mSongSheetListeners) {
                childListener =  listener
            }
        }
        var isCanScroll = !childListener.onTouchEvent(event)
//        viewPager.setNoScroll(isCanScroll)
        return isCanScroll
    }

    override fun onDestroy() {
        super.onDestroy()
        curActivity.unRegisterMyTouchListener(this)
    }

    override fun loadData() {

    }

    fun registerMyTouchListener(listener: SongSheetTouchListener) {
        this.mSongSheetListeners.add(listener)
    }

    fun unRegisterMyTouchListener(listener: SongSheetTouchListener) {
        this.mSongSheetListeners.remove(listener)
    }

    interface SongSheetTouchListener {
        fun onTouchEvent(event : MotionEvent) : Boolean
    }

}