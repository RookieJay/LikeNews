package pers.ll.likenews.view.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.DailyArticle
import pers.ll.likenews.model.DailyArticleResult
import pers.ll.likenews.utils.GsonConverterFactory
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DailyArticleFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mRefreshLayout : SwipeRefreshLayout
    private lateinit var tvArticleTitle : TextView
    private lateinit var tvAuthor : TextView
    private lateinit var tvContent : TextView
    private val mExecutor = ThreadPoolManager.getInstance()
    private val mMainThread = MainHandler.getInstance()

    override fun setContentView(): Int {
        return R.layout.fragment_daily_article
    }

    override fun initView() {
        mRefreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        tvArticleTitle = findViewById(R.id.tvArticleTitle) as TextView
        tvAuthor = findViewById(R.id.tvAuthor) as TextView
        tvContent = findViewById(R.id.tvContent) as TextView
        mRefreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
        mRefreshLayout.isRefreshing = true
        mExecutor.execute {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_DAILY_ARTICLE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            apiService.randomArticle(1).enqueue(object : Callback<DailyArticleResult> {
                override fun onFailure(call: Call<DailyArticleResult>, t: Throwable) {
                    showFailure()
                }

                override fun onResponse(call: Call<DailyArticleResult>, response: Response<DailyArticleResult>) {
                    val body = response.body()
                    val article = body?.data
                    showData(article)
                }
            })
        }
    }

    private fun showFailure() {
        mMainThread.post {
            mRefreshLayout.isRefreshing = false
            ToastUtils.showShort("网络连接失败")
        }
    }

    private fun showData(article: DailyArticle?) {
        mMainThread.post {
            tvArticleTitle.text = article?.title
            tvAuthor.text = article?.author
            tvContent.text = article?.content
            mRefreshLayout.isRefreshing = false
        }
    }

    override fun onRefresh() {
        loadData()
    }


}