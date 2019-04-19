package pers.ll.likenews.view.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class HotNewsPagerAdapter(fm: FragmentManager, titles: Array<String>) : FragmentPagerAdapter(fm) {

    private var fragmentList = arrayListOf<Fragment>()
    private var mTitles  = titles

    fun setData(fragments : ArrayList<Fragment>) {
        this.fragmentList = fragments
    }


    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}
