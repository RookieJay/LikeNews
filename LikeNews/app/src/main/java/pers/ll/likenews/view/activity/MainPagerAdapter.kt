package pers.ll.likenews.view.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import pers.ll.likenews.consts.Const
import pers.ll.likenews.view.fragment.CenterFragment


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentList = arrayListOf<Fragment>()
    private lateinit var fm: FragmentManager
    private var type = Const.Type.TYPE_NEWS

    fun setData(fragments : ArrayList<Fragment>?, fm : FragmentManager) {
        this.fragmentList = fragments!!
        this.fm = fm
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    /**
     * getItem是创建一个新的Fragment，但是这个方法名可能会被误认为是返回一个已经存在的Fragment。
     */
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getItemId(position: Int): Long {
        return fragmentList[position].hashCode().toLong()
    }

    /**
     * 调用notifyDataSetChanged()时，2个adapter(pagerAdapter和PagerStateAdapter)的方法的调用情况相同，
     * 当前页和相邻的两页的getItemPosition都会被调用到。
     */
    override fun getItemPosition(`object`: Any): Int {
        if (`object` is CenterFragment) {
            `object`.changePage(type)
        }
        return super.getItemPosition(`object`)
    }

    fun refreshData(fragType: Int) {
        type = fragType
        notifyDataSetChanged()
    }


    //    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val fragment = fragmentList[position]
//        //判断当前fragment是否被加入FragmentManager中
//        if (!fragment.isAdded) {
//            val trans = fm.beginTransaction()
//            trans.add(fragment, fragment.javaClass.simpleName)
//            trans.commitAllowingStateLoss()
//            fm.executePendingTransactions()
//        }
//        if (fragment.view!!.parent == null) {
//            container.addView(fragment.view)
//        }
//        return fragment.view!!
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        //移除布局
//        container.removeView(fragmentList[position].view)
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
}
