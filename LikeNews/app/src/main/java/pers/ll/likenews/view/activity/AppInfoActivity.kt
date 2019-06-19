package pers.ll.likenews.view.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_app_info.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseActivity
import pers.ll.likenews.consts.Const
import pers.ll.likenews.utils.AppManager

class AppInfoActivity : BaseActivity() {

    private lateinit var appManager : AppManager
    private lateinit var mAdapter : PermissionsAdapter
    private var mPermissions: Array<String>? = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)
        appManager = AppManager.getInstance(this)
        initView()
        initData()

    }

    private fun initView() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        barLeft.setOnClickListener { finish() }
        mAdapter = PermissionsAdapter(emptyArray())
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRecyclerView.adapter = mAdapter

    }

    private fun initData() {
        val intent = intent
        if (null == intent) {
            finish()
            return
        }
        val packageName = intent.getStringExtra(Const.Key.KEY_PACKAGE_NAME)
        val isSysApp = intent.getBooleanExtra(Const.Key.KEY_FLAG_SYS_APP, true)
        val name = appManager.getAppName(packageName)
        val icon = appManager.getAppIcon(packageName)
        val size = appManager.getAppSize(packageName)
        val version = appManager.getAppVersion(packageName)
        tv_app_name.text = name
        barTitle.text = name
        tvAppVersion.text = version
        tvAppSize.text = size
        Glide.with(this).load(icon).into(iv_app_icon)
        mPermissions = appManager.getAppPermissions(packageName) as Array<String>?
        if (mPermissions != null) {
            mAdapter.refresh(mPermissions)
        }
        btUninstall.isEnabled = !isSysApp
        btUninstall.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.dialog_title))
                    .setIcon(icon)
                    .setMessage("您确定要卸载" + name + "应用吗？")
                    .setPositiveButton("确定") { _, _ -> appManager.unInstallApp(packageName) }
                    .setNegativeButton("取消", null)
                    .show()

        }
    }


}
