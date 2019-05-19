package pers.ll.likenews.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_whether.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.MXWhether
import pers.ll.likenews.model.MxWhetherResult
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WhetherActivity : AppCompatActivity() {

    private lateinit var mAdapter : ForecastAdapter
    private lateinit var forecasts : ArrayList<MXWhether.Forecast>
    private var mExecutor = ThreadPoolManager.getInstance()
    private var mMainThread = MainHandler.getInstance()
    private var imageUtil = ImageUtil.getInstance()
    private lateinit var whether: MXWhether

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_whether)
        initView()
        loadData()
        setListener()
    }

    private fun initView() {
        val params = toolBar.layoutParams as LinearLayout.LayoutParams
        params.setMargins(0, SystemUtils.getStatusBarHeight(resources), 0, 0)
        initBackground()
        mAdapter = ForecastAdapter(ArrayList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun initBackground() {
        Glide.with(ivBackground).load(Const.URL.BING_DAILY_PIC).into(ivBackground)
    }

    private fun loadData() {
        mExecutor.execute {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MXWHETHER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            apiService.getMXWhether("101270101").enqueue(object : Callback<MxWhetherResult>{
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

    private fun setListener() {
        barLeft.setOnClickListener {
            finish()
        }
        barTitle.setOnClickListener {
            startActivity(Intent(this, CitySelectActivity :: class.java))
        }
    }

    private fun showData(whether: MXWhether) {
        mMainThread.post {
            barTitle.text = whether.city
            tvBarRight.text = TimeUtils.getFriendlyTimeSpanByNow(whether.realtime.time)
            tvTemperature.text = String.format("%s°C", whether.realtime.sendibleTemp)
            tvRealTimeWhether.text = whether.realtime.weather
            tvPm25Status.text = whether.pm25.quality
            tvAQI.text = whether.pm25.aqi
            tvPM25.text = whether.pm25.pm25
            forecasts = whether.weathers as ArrayList<MXWhether.Forecast>
            if (null != forecasts && forecasts.size > 0) {
                mAdapter.replaceAll(forecasts)
                mAdapter.notifyDataSetChanged()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}
