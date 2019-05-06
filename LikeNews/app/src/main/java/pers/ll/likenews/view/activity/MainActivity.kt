package pers.ll.likenews.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Whether
import pers.ll.likenews.model.WhetherResult
import pers.ll.likenews.ui.CircleImageView
import pers.ll.likenews.utils.GsonConverterFactory
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils
import pers.ll.likenews.view.fragment.HomeFragment
import pers.ll.likenews.view.fragment.CenterFragment
import pers.ll.likenews.view.fragment.PersonalFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : MainPagerAdapter
    private var fragments : ArrayList<Fragment> = ArrayList()
    private var menuItem : MenuItem? = null
    //侧滑菜单头布局
    private lateinit var ivWhether: ImageView
    private lateinit var tvWhether: TextView
    private lateinit var tvTemp : TextView
    private lateinit var ivAvatar : CircleImageView
    private lateinit var tvUserName : TextView
    private lateinit var tvInstruction : TextView
    private var centerTitle = "立刻新闻"
    private var fragType = 0
    private val TYPE_NEWS = 1
    private val TYPE_MUSIC = 2
    private val TYPE_MOVIE = 3

    private val handler = WhetherHandler(this)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.setCurrentItem(0, true)
                barTitle.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.setCurrentItem(1, true)
                when(fragType) {
                    TYPE_NEWS -> barTitle.setText(R.string.title_news)
                    TYPE_MUSIC -> barTitle.setText(R.string.title_music)
                    TYPE_MOVIE -> barTitle.setText(R.string.title_movie)
                }
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
        loadData()
        setListener()
    }

    private fun initDrawer() {
        val headerLayout = navigationView.getHeaderView(0)  //这种方式用于显示头布局
        ivWhether = headerLayout.findViewById(R.id.ivWhether)
        tvWhether = headerLayout.findViewById(R.id.tvWhether)
        tvUserName = headerLayout.findViewById(R.id.tvUserName)
        tvTemp = headerLayout.findViewById(R.id.tvTemp)
        ivAvatar = headerLayout.findViewById(R.id.ivAvatar)
        tvInstruction = headerLayout.findViewById(R.id.tvInstruction)
        navigationView.itemIconTintList = null
        tvUserName.text = "Lika"
        tvInstruction.text = "春风十里不如你"
        ivAvatar.setImageResource(R.mipmap.icon_avatar)
        navigationView.menu.getItem(0).isChecked = true
    }


    private fun initView() {
        initDrawer()
        initToolbar()
        initBottomNavigation()
        viewPager.setCurrentItem(0, true)
        viewPager.offscreenPageLimit = 3
        adapter = MainPagerAdapter(supportFragmentManager)
        initData()
        viewPager.adapter = adapter
    }

    private fun initToolbar() {
        barTitle.text = "我的首页"
        barLeft.setImageResource(R.drawable.ic_menu)
        barLeft.visibility = View.VISIBLE
    }

    private fun initBottomNavigation() {
//        menu_navigation_bottom.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
    }

    private fun initData() {
        val homeFragment = HomeFragment()
        val centerFragment = CenterFragment()
        val personalFragment = PersonalFragment()
        val bundle = Bundle()
        bundle.putInt(Const.Key.START_TYPE, fragType)
        centerFragment.arguments = bundle
        fragments.add(homeFragment)
        fragments.add(centerFragment)
        fragments.add(personalFragment)
        adapter!!.setData(fragments, supportFragmentManager)
    }

    private fun refreshCenterFragment() {
        if (viewPager.currentItem == 1) {
            when(fragType) {
                TYPE_NEWS -> barTitle.setText(R.string.title_news)
                TYPE_MUSIC -> barTitle.setText(R.string.title_music)
                TYPE_MOVIE -> barTitle.setText(R.string.title_movie)
            }
        }
        val centerFragment = CenterFragment()
        val bundle = Bundle()
        bundle.putInt(Const.Key.START_TYPE, fragType)
        centerFragment.arguments = bundle
        if (fragments.size > 0 && adapter != null) {
            fragments[1] = centerFragment
            adapter!!.refreshData(fragments)
        }
    }

    private fun loadData() {
            ThreadPoolManager.getInstance().execute(Runnable {
                val retrofit = Retrofit
                    .Builder()
                    .baseUrl(Const.URL.BASE_URL_WHETHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiService = retrofit.create(ApiService :: class.java)
                val msg = handler.obtainMessage()
                apiService.getWhether("绵阳").enqueue(object : Callback<WhetherResult> {
                    override fun onFailure(call: Call<WhetherResult>, t: Throwable) {
                        msg.arg1 = Const.RESULT_CODE.Args_Failure
                        handler.sendMessage(msg)
                    }

                    override fun onResponse(call: Call<WhetherResult>, response: Response<WhetherResult>) {
                        msg.arg1 = Const.RESULT_CODE.Args_Success
                        val body = response.body()
                        if (null != body) {
                            val whether = body.data
                            if (null != whether) {
                                val bundle = Bundle()
                                bundle.putParcelable(Const.Key.KEY_WHETHER, whether)
                                msg.data = bundle
                            } else {
                                msg.arg1 = Const.RESULT_CODE.Args_Empty
                            }
                        } else {
                            msg.arg1 = Const.RESULT_CODE.Args_Empty
                        }
                        handler.sendMessage(msg)
                    }
                })
            })
    }

    private fun setListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(i: Int) {
                menuItem = bottomNavigation.menu.getItem(i)
                val menuItem1 = menuItem
                menuItem1!!.isChecked = true
                viewPager.setCurrentItem(i, true)
                when(i) {
                    0 -> barTitle.setText(R.string.title_home)
                    1 -> when(fragType) {
                        0 -> barTitle.setText(R.string.title_news)
                        TYPE_NEWS -> barTitle.setText(R.string.title_news)
                        TYPE_MUSIC -> barTitle.setText(R.string.title_music)
                        TYPE_MOVIE -> barTitle.setText(R.string.title_movie)
                    }
                    2 -> barTitle.setText(R.string.title_personal)
                }
            }
        })
        barLeft.setOnClickListener {
            drawerLayout.openDrawer(Gravity.START, true)
        }
        navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.closeDrawer(Gravity.START, true)
            centerTitle = String.format("立刻%s", it.title)
            bottomNavigation.menu.getItem(1).title = centerTitle
            when (it.itemId) {
                R.id.menu_news -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_news)
                    fragType = TYPE_NEWS
                }
                R.id.menu_music -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_music)
                    fragType = TYPE_MUSIC
                }
                R.id.menu_movie -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_movie)
                    fragType = TYPE_MOVIE
                }
            }
            refreshCenterFragment()
            return@setNavigationItemSelectedListener true
        }
    }


    private fun showWhetherInfo(whether: Whether?) {
        if (whether != null) {
            ivWhether.setImageResource(R.drawable.ic_wb_sunny)
            tvWhether.text = whether.forecast[0].type
//            tvTemp.text = String.format("%s-%s", Utils.get_None_CN_Str(whether.forecast[0].low), Utils.get_None_CN_Str(whether.forecast[0].high))
            tvTemp.text = String.format("%s°C", whether.wendu)
        }
    }

    private fun onFailure() {
        ToastUtils.showShort("天气查询失败")
    }

    private fun showEmpty() {
        ToastUtils.showShort("天气查询为空")
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { add(frameId, fragment) }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction{replace(frameId, fragment)}

    class WhetherHandler(mainActivity: MainActivity) : Handler() {

        private var activity = mainActivity

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (null != msg) {
                val bundle = msg.data
                if (null != bundle) {
                    val whether = bundle.getParcelable<Whether>(Const.Key.KEY_WHETHER)
                    when(msg.arg1) {
                        Const.RESULT_CODE.Args_Success -> activity.showWhetherInfo(whether)
                        Const.RESULT_CODE.Args_Failure -> activity.onFailure()
                        Const.RESULT_CODE.Args_Empty -> activity.showEmpty()
                    }
                } else{
                    activity.showEmpty()
                }

            }
        }
    }
}
