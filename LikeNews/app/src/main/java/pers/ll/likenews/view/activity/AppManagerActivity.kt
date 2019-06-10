package pers.ll.likenews.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pers.ll.likenews.R
import pers.ll.likenews.model.AppModel
import android.support.v7.widget.RecyclerView
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import pers.ll.likenews.utils.AppManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_app_manager.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.consts.Const
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils

class AppManagerActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener, SwipeRefreshLayout.OnRefreshListener, AppManagerAdapter.OnItemClickListener {

    private lateinit var tab: TabLayout
    private lateinit var mRecyclerView: RecyclerView
    private val mStrTitle = arrayOf("已安装", "系统应用", "全部")
    //给适配器使用
    private var mList : ArrayList<AppModel> = ArrayList()
    //所有的应用信息
    private lateinit var mAllList : ArrayList<AppModel>
    private var currentTab = 0
    private lateinit var mAppManagerAdapter: AppManagerAdapter
    private var mExecutor = ThreadPoolManager.getInstance()
    private var mMainThread = MainHandler.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_manager)
        initView()
    }

    private fun initView() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        barTitle.text = "应用管理"
        tab = findViewById(R.id.app_mytab)
        mRecyclerView = findViewById(R.id.app_rv)
        mAppManagerAdapter = AppManagerAdapter(this, mList)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAppManagerAdapter
        tab.addOnTabSelectedListener(this)
        refreshLayout.setOnRefreshListener(this)
        mAppManagerAdapter.setOnItemClickListener(this)
        barLeft.setOnClickListener { finish() }
        initData()
    }

    private fun initData() {
        refreshLayout.isRefreshing = true
        mExecutor.execute {
            mAllList = AppManager.getInstance(this).allApp as ArrayList<AppModel>
            if (mAllList.size > 0) {
                mMainThread.post {
                    for (str in mStrTitle) {
                        tab.addTab(tab.newTab().setText(str))
                    }
                    parsingApp(0)
                    refreshLayout.isRefreshing = false
                }
            } else {
                mMainThread.post {
                    ToastUtils.showShort("发生未知错误，未获取到应用信息")
                    refreshLayout.isRefreshing = false
                }
            }
        }


    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        currentTab = tab.position
        parsingApp(currentTab)
        if (currentTab == 2) {
            mList.addAll(mAllList)
            mAppManagerAdapter.notifyDataSetChanged()
        }
    }

    private fun parsingApp(currentTab: Int) {
        this.currentTab = currentTab
        //防止数据重复
        if (mList.size > 0) {
            mList.clear()
        }
        for (appModel in mAllList) {
            when (currentTab) {
                0 -> if (!appModel.isSystem) {
                    mList.add(appModel)
                }
                1 -> if (appModel.isSystem) {
                    mList.add(appModel)
                }
            }
        }
        //刷新适配器
        mAppManagerAdapter.notifyDataSetChanged()
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {

    }

    override fun onTabReselected(tab: TabLayout.Tab) {

    }

    override fun onRefresh() {
        mExecutor.execute {
            mAllList = AppManager.getInstance(this).allApp as ArrayList<AppModel>
            mMainThread.post{
                parsingApp(currentTab)
                refreshLayout.isRefreshing = false
            }
        }
    }

    override fun onItemClick(appModel: AppModel, position: Int) {
        val intent = Intent(this, AppInfoActivity :: class.java)
        intent.putExtra(Const.Key.KEY_PACKAGE_NAME, appModel.packageName)
        intent.putExtra(Const.Key.KEY_FLAG_SYS_APP, appModel.isSystem)
        startActivity(intent)
    }

}