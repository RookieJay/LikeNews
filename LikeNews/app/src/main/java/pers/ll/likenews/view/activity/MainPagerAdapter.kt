package pers.ll.likenews.view.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup


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

    override fun getItemId(position: Int): Long {
        return fragmentList[position].hashCode().toLong()
    }


    fun refreshData(fragments: ArrayList<Fragment>) {
//        var trans : FragmentTransaction? = fm.beginTransaction()  //获得FragmentTransaction 事务
//        for (f in this.fragmentList) {
//            trans?.remove(f) //遍历删除fragment
//        }
//        trans?.commit()
//        trans = null
//        fm.executePendingTransactions() //提交事务
        this.fragmentList = fragments
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
