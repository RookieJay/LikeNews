package pers.ll.likenews.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Whether
import pers.ll.likenews.model.WhetherResult
import pers.ll.likenews.ui.ChildViewPager
import pers.ll.likenews.ui.CircleImageView
import pers.ll.likenews.utils.GsonConverterFactory
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils
import pers.ll.likenews.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeFragment : BaseFragment() {

    private lateinit var titles : Array<String>
    private lateinit var fragments : ArrayList<Fragment>
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ChildViewPager
    private lateinit var adapter : HotNewsPagerAdapter
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var ivLeft : ImageView
    private lateinit var tvTitle : TextView
    private lateinit var navigationView : NavigationView
    private val handler = WhetherHandler(this)
    //侧滑菜单头布局
    private lateinit var ivWhether: ImageView
    private lateinit var tvWhether: TextView
    private lateinit var tvTemp : TextView
    private lateinit var ivAvatar : CircleImageView
    private lateinit var tvUserName : TextView
    private lateinit var tvInstruction : TextView

    override fun setContentView(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initDrawer()
//        initToolBar()
        initViewData()
        initViewPager()
        initTab()
        setListener()
    }

    private fun initDrawer() {
        drawerLayout = findViewById(R.id.drawerLayout) as DrawerLayout
        navigationView = findViewById(R.id.navigationView) as NavigationView
        val headerLayout = navigationView.getHeaderView(0)  //这种方式用于显示头布局
        ivWhether = headerLayout.findViewById(R.id.ivWhether)
        tvWhether = headerLayout.findViewById(R.id.tvWhether)
        tvUserName = headerLayout.findViewById(R.id.tvUserName)
        tvTemp = headerLayout.findViewById(R.id.tvTemp)
        ivAvatar = headerLayout.findViewById(R.id.ivAvatar)
        tvInstruction = headerLayout.findViewById(R.id.tvInstruction)
        navigationView.itemIconTintList = null

    }

    private fun initToolBar() {
        tvTitle = findViewById(R.id.barTitle) as TextView
        ivLeft = findViewById(R.id.barLeft) as ImageView
        tvTitle.text = "我的首页"
        ivLeft.setImageResource(R.drawable.ic_menu)
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
//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(p0: Int) {
//            }
//
//            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//
//            }
//
//            override fun onPageSelected(p0: Int) {
//            }
//        })
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
        ivLeft.setOnClickListener{
            drawerLayout.openDrawer(Gravity.START, true)
        }
        navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.closeDrawer(Gravity.START, true)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun loadData() {
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

    private fun showWhetherInfo(whether: Whether?) {
        if (whether != null) {
            ivWhether.setImageResource(R.drawable.ic_wb_sunny)
            tvWhether.text = whether.forecast[0].type
//            tvTemp.text = String.format("%s-%s", Utils.get_None_CN_Str(whether.forecast[0].low), Utils.get_None_CN_Str(whether.forecast[0].high))
            tvTemp.text = String.format("%s°C", whether.wendu)
            tvUserName.text = "Lika"
            tvInstruction.text = "春风十里不如你"
            ivAvatar.setImageResource(R.mipmap.icon_avatar)

        }
    }

    private fun onFailure() {
        ToastUtils.showShort("天气查询失败")
    }

    private fun showEmpty() {
        ToastUtils.showShort("天气查询为空")
    }


    class WhetherHandler(homeFragment: HomeFragment) : Handler() {

        private var fragment = homeFragment

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (null != msg) {
                val bundle = msg.data
                if (null != bundle) {
                    val whether = bundle.getParcelable<Whether>(Const.Key.KEY_WHETHER)
                    when(msg.arg1) {
                        Const.RESULT_CODE.Args_Success -> fragment.showWhetherInfo(whether)
                        Const.RESULT_CODE.Args_Failure -> fragment.onFailure()
                        Const.RESULT_CODE.Args_Empty -> fragment.showEmpty()
                    }
                } else{
                    fragment.showEmpty()
                }

            }
        }
    }

}
