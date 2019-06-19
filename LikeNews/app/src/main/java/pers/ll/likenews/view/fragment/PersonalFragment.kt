package pers.ll.likenews.view.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.consts.Const.URL.BASE_URL_OPEN_API
import pers.ll.likenews.model.BaseResult
import pers.ll.likenews.model.Poetry
import pers.ll.likenews.ui.MyTextView
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PersonalFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var rlPoetry : RelativeLayout
    private lateinit var llPoetry : LinearLayout
    private lateinit var tvTitle : MyTextView
    private lateinit var tvAuthor : MyTextView
    private lateinit var sentences : ArrayList<String>
    private lateinit var adapter : PoetryAdapter
    private lateinit var recyclerView : RecyclerView

    private var executor = ThreadPoolManager.getInstance()
    private var mainThread = MainHandler.getInstance()
    private var imageUtil = ImageUtil.getInstance()


    override fun setContentView() : Int {
        return R.layout.fragment_personal
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.srlPoetry) as SwipeRefreshLayout
        rlPoetry = findViewById(R.id.rlPoetry) as RelativeLayout
        llPoetry = findViewById(R.id.llPoetry) as LinearLayout
        tvTitle = findViewById(R.id.tvTitle) as MyTextView
        tvAuthor = findViewById(R.id.tvAuthor) as MyTextView
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        tvTitle.isFocusable = true
        tvTitle.isFocusableInTouchMode = true
        tvTitle.isSelected = true
        refreshLayout.setColorSchemeColors(
                UIUtils.getColor(context, android.R.color.holo_blue_light), UIUtils.getColor(context,android.R.color.holo_red_light),
                UIUtils.getColor(context,android.R.color.holo_green_light), UIUtils.getColor(context,android.R.color.holo_orange_light))
        setListener()
        executor.execute {
            val bitmap = imageUtil.url2BitMap(Const.URL.BING_DAILY_PIC)
            if (bitmap != null) {
                //启用高斯模糊
                val overLay = imageUtil.rsBlur(rlPoetry.context, bitmap, 24, 1f / 8f)
                mainThread.post {
                    rlPoetry.background = imageUtil.getDrawbleFormBitmap(rlPoetry.context, overLay) }
            }
        }
        sentences = ArrayList()
        adapter = PoetryAdapter(sentences)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setListener() {
        refreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        loadData()
    }

    private fun startRefresh() {
        mainThread.post {
            refreshLayout.isRefreshing = true
        }
    }

    private fun finishRefresh() {
        refreshLayout.isRefreshing = false
    }

    override fun loadData() {
        executor.execute {
            startRefresh()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL_OPEN_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            apiService.getRandomRecommendPoetry("recommendPoetry").enqueue(object : Callback<BaseResult<Poetry>>{
                override fun onFailure(call: Call<BaseResult<Poetry>>, t: Throwable) {
                    onFailure()
                }

                override fun onResponse(call: Call<BaseResult<Poetry>>, response: Response<BaseResult<Poetry>>) {
                    val body = response.body()
                    if (body != null) {
                        val poetry = body.result
                        if (poetry != null) {
                            mainThread.post {
                                showData(poetry)
                            }
                        } else{
                            showEmpty()
                        }
                    } else {
                        showEmpty()
                    }
                }

            })
        }
    }

    /**
     * 设置textSize :px sp dip(dp)
     * setTextSize(int unit, int size)
     * TypedValue.COMPLEX_UNIT_PX : Pixels
     * TypedValue.COMPLEX_UNIT_SP : Scaled Pixels
     * TypedValue.COMPLEX_UNIT_DIP : Device Independent Pixels
     */

    private fun showData(poetry: Poetry) {
        tvTitle.text = poetry.title
        tvAuthor.text = poetry.authors
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) //match和wrap
//        layoutParams.setMargins(0, UIUtils.dp2px(context, 10f), 0, 0)
        sentences = poetry.content.split("|") as ArrayList<String>
//        for ((index, sentence) in list.withIndex()) {
//            val tvSentence = MyTextView(context)
//            tvSentence.text = sentence
//            tvSentence.setTextColor(ContextCompat.getColor(context, R.color.white))
//            tvSentence.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
//            tvSentence.gravity = Gravity.CENTER
//            tvSentence.layoutParams = layoutParams
//            llPoetry.addView(tvSentence)
//        }
        adapter.replaceAll(sentences)
        finishRefresh()
    }

    private fun showEmpty() {
        finishRefresh()
    }

    private fun onFailure() {
        ToastUtils.showShort("连接失败")
        finishRefresh()
    }

}
