package pers.ll.likenews.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raizlabs.android.dbflow.sql.language.Select
import kotlinx.android.synthetic.main.activity_city_select.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.consts.Const
import pers.ll.likenews.mock.MockData
import pers.ll.likenews.model.City
import pers.ll.likenews.model.MXWhether
import pers.ll.likenews.model.MxWhetherResult
import pers.ll.likenews.utils.GsonConverterFactory
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CitySelectActivity : AppCompatActivity() {

    private var mExecutor = ThreadPoolManager.getInstance()
    private var mMainThread = MainHandler.getInstance()
    private lateinit var mWhethers : ArrayList<MXWhether>
    private lateinit var mAdapter : WhetherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_city_select)
        initView()
        checkLoadData()
        initData()
        setListener()
    }

    private fun initView() {
        initToolbar()
        mAdapter = WhetherListAdapter(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter
    }

    private fun initToolbar() {
        toolBar.background = ContextCompat.getDrawable(applicationContext, R.color.black)
        barLeft.visibility = View.VISIBLE
        barTitle.text = "选择城市"
    }

    private fun initData() {
        mExecutor.execute {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MXWHETHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            //默认成都
            apiService.getMXWhether("101270101").enqueue(object : Callback<MxWhetherResult> {
                override fun onFailure(call: Call<MxWhetherResult>, t: Throwable) {

                }

                override fun onResponse(call: Call<MxWhetherResult>, response: Response<MxWhetherResult>) {
                    val body = response.body()
                    if (null != body) {
                        val whether = body.value[0]
                        if (null != whether) {
                            showData(whether)
                        }
                    }
                }

            })

        }
    }

    private fun showData(whether: MXWhether) {
        val list = ArrayList<MXWhether>()
        list.add(whether)
        mAdapter.replaceAll(list)
        mAdapter.notifyDataSetChanged()
    }

    private fun checkLoadData() {
        mExecutor.execute {
            //查询
            val cityList = Select().from(City :: class.java).queryList()
            if ( cityList.size > 0) {
                ToastUtils.showShort("城市个数" + cityList.size.toString())
            } else {
                //插入
                val data = MockData(applicationContext, "cities.json")
                val json = data.json
                val type = object : TypeToken<ArrayList<City>>() {}.type
                val cities : ArrayList<City> = Gson().fromJson(json, type)
                for (city in cities) {
                    city.save()
                    ToastUtils.showShort("城市数据自动导入成功")
                }
            }
        }
    }

    private fun setListener() {
        barLeft.setOnClickListener {
            finish()
        }
        fab.setOnClickListener {
            startActivityForResult(Intent(this, CitySearchActivity :: class.java), Const.RESULT_CODE.CODE_SEARCH)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}
