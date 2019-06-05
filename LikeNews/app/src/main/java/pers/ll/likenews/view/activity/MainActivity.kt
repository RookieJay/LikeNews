package pers.ll.likenews.view.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
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
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_layout_drawer_navigation.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.MXWhether
import pers.ll.likenews.model.MxWhetherResult
import pers.ll.likenews.ui.CircleImageView
import pers.ll.likenews.utils.*
import pers.ll.likenews.view.fragment.HomeFragment
import pers.ll.likenews.view.fragment.CenterFragment
import pers.ll.likenews.view.fragment.PersonalFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    // 保存MyTouchListener接口的列表
    private  var mTouchListeners = ArrayList<MyTouchListener>()

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
    private val TYPE_DEFAULT = 0
    private val TYPE_NEWS = 1
    private val TYPE_MUSIC = 2
    private val TYPE_MOVIE = 3
    private var executor = ThreadPoolManager.getInstance()
    private var imageUtil = ImageUtil.getInstance()
    private val handler = WhetherHandler(this)
    private lateinit var mWhether: MXWhether
    private lateinit var bgBitmap : Bitmap
    private var recordTime = 0L

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
        UIUtils.setSameColorBar(true, window, resources)
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
        executor.execute(Runnable {
            bgBitmap = imageUtil.url2BitMap(Const.URL.BING_DAILY_PIC)
            if (bgBitmap != null) {
                //启用高斯模糊
                val bluredBM = imageUtil.rsBlur(applicationContext, bgBitmap, 5, 1f / 8f)
                MainHandler.getInstance().post {
                    rlHeader.background = imageUtil.getDrawbleFormBitmap(applicationContext, bluredBM) }
            }
        })
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //将侧边栏顶部延伸至status bar
            drawerLayout.fitsSystemWindows = true
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            drawerLayout.clipToPadding = false
        }
    }

    private fun initView() {
        initDrawer()
        initToolbar()
        initBottomNavigation()
        viewPager.setCurrentItem(0, true)
        viewPager.offscreenPageLimit = 3
        viewPager.setNoScroll(true)
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
        adapter.setData(fragments, supportFragmentManager)
    }

    private fun refreshCenterFragment(type: Int, it: MenuItem) {
        fragType = type
        centerTitle = String.format("立刻%s", it.title)
        if (viewPager.currentItem == 1) {
            when(fragType) {
                TYPE_NEWS -> barTitle.setText(R.string.title_news)
                TYPE_MUSIC -> barTitle.setText(R.string.title_music)
                TYPE_MOVIE -> barTitle.setText(R.string.title_movie)
            }
        }
        adapter.refreshData( fragType)
    }

    private fun loadData() {
        val msg = handler.obtainMessage()
        executor.execute {
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.URL.BASE_URL_MXWHETHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(ApiService::class.java)
            apiService.getMXWhether("101270101").enqueue(object : Callback<MxWhetherResult> {
                override fun onFailure(call: Call<MxWhetherResult>, t: Throwable) {

                }

                override fun onResponse(call: Call<MxWhetherResult>, response: Response<MxWhetherResult>) {
                    val body = response.body()
                    if (null != body) {
                        val whether = body.value[0]
                        if (null != whether) {
                            msg.arg1 = Const.RESULT_CODE.Args_Success
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
        }
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
                        TYPE_DEFAULT -> barTitle.setText(R.string.title_news)
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
            when (it.itemId) {
                R.id.menu_news -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_news)
                    refreshCenterFragment(TYPE_NEWS, it)
                }
                R.id.menu_music -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_music)
                    refreshCenterFragment(TYPE_MUSIC, it)
                }
                R.id.menu_movie -> {
                    bottomNavigation.menu.getItem(1).icon = ContextCompat.getDrawable(applicationContext, R.drawable.vector_drawable_icon_menu_movie)
                    refreshCenterFragment(TYPE_MOVIE, it)
                }
                R.id.menu_whether -> {
                    if (null != mWhether) {
                        ToastUtils.showShort(mWhether.indexes[0].content)
                    }
                    val intent = Intent(this, WhetherActivity :: class.java)
                    val bundle = Bundle()
                    bundle.putParcelable(Const.Key.KEY_WHETHER, mWhether)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                R.id.menu_exit -> {
//                   //调用系统API结束进程
                    android.os.Process.killProcess(android.os.Process.myPid())
                    //结束整个虚拟机进程，注意如果在manifest里用android:process给app指定了不止一个进程，则只会结束当前进程
                    System.exit(0)
                    finish()
//                    startActivity(Intent(this, ChooseFunctionActivity :: class.java))
                }
            }
            bottomNavigation.menu.getItem(1).title = centerTitle
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            checkExit()
            return false
        }
        return super.onKeyDown(keyCode, event)

    }

    private fun checkExit() {
        if (System.currentTimeMillis() - recordTime > 2000) {
            ToastUtils.showShort("再按一次退出程序")
            recordTime = System.currentTimeMillis()
        } else {
            finish()
            System.exit(0)
        }
    }

    private fun showWhetherInfo(whether: MXWhether) {
        if (whether != null) {
            mWhether = whether
            ivWhether.setImageResource(R.drawable.ic_wb_sunny)
            tvWhether.text = whether.weathers[0].weather
//            tvTemp.text = String.format("%s-%s", Utils.get_None_CN_Str(whether.forecast[0].low), Utils.get_None_CN_Str(whether.forecast[0].high))
            tvTemp.text = String.format("%s°C", whether.realtime.temp)
        }
    }

    private fun onFailure() {
        ToastUtils.showShort("天气查询失败")
    }

    private fun showEmpty() {
        ToastUtils.showShort("天气查询为空")
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
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
                    val whether = bundle.getParcelable<MXWhether>(Const.Key.KEY_WHETHER)
                    if (whether != null) {
                        when(msg.arg1) {
                            Const.RESULT_CODE.Args_Success -> activity.showWhetherInfo(whether)
                            Const.RESULT_CODE.Args_Failure -> activity.onFailure()
                            Const.RESULT_CODE.Args_Empty -> activity.showEmpty()
                        }
                    } else {
                        activity.showEmpty()
                    }

                } else{
                    activity.showEmpty()
                }

            }
        }
    }

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     */
    fun registerMyTouchListener(listener : MyTouchListener ) {
        mTouchListeners.add(listener)
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     */
    fun unRegisterMyTouchListener(listener : MyTouchListener ) {
        mTouchListeners.remove(listener)
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (mTouchListeners.size > 0) {
            for (listener in mTouchListeners) {
                listener.onTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    interface MyTouchListener {
        fun onTouchEvent(event : MotionEvent) : Boolean
    }
}
