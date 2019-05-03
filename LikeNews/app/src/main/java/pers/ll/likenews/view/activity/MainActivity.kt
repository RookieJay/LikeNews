package pers.ll.likenews.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.view.fragment.HomeFragment
import pers.ll.likenews.view.fragment.NewsFragment
import pers.ll.likenews.view.fragment.PersonalFragment

class MainActivity : AppCompatActivity() {

    private var adapter : MainPagerAdapter? = null
    private var fragments : ArrayList<Fragment>? = ArrayList()
    private var menuItem : MenuItem? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0, true)
                barTitle.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.setCurrentItem(1, true)
                barTitle.setText(R.string.title_news)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_personal -> {
                viewPager.setCurrentItem(2, true)
                barTitle.setText(R.string.title_personal)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setListener()
    }

    private fun setListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(i: Int) {
                menuItem = navigation.menu.getItem(i)
                val menuItem1 = menuItem
                menuItem1!!.isChecked = true
                viewPager.setCurrentItem(i, true)
            }
        })
    }

    private fun initView() {
        initToolbar()
        initNavigation()
        viewPager.setCurrentItem(0, true)
        viewPager.offscreenPageLimit = 3
        adapter = MainPagerAdapter(supportFragmentManager)
        initData()
        viewPager.adapter = adapter
    }

    private fun initNavigation() {
//        menu_navigation_bottom.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
    }

    private fun initData() {
        fragments!!.add(HomeFragment())
        fragments!!.add(NewsFragment())
        fragments!!.add(PersonalFragment())
        adapter!!.setData(fragments, supportFragmentManager)
    }

    private fun initToolbar() {
//        barTitle.text = resources.getString(R.string.title_home)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { add(frameId, fragment) }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction{replace(frameId, fragment)}
}
