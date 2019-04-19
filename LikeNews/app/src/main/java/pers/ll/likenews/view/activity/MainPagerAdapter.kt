package pers.ll.likenews.view.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentList = arrayListOf<Fragment>()
    private lateinit var fm: FragmentManager

    fun setData(fragments : ArrayList<Fragment>?, fm : FragmentManager) {
        this.fragmentList = fragments!!
        this.fm = fm
    }


    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
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
