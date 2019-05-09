package pers.ll.likenews.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.News
import pers.ll.likenews.model.NewsResult
import pers.ll.likenews.model.NewsType
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.view.activity.WebActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference

class NewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, HotNewsAdapter.ItemClickCallBack {

    private val START_TYPE = 1
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var adapter: HotNewsAdapter
    private lateinit var newsList : ArrayList<News>
    private lateinit var handler : NewsHandler    //防止内存泄漏
    private var executor = ThreadPoolManager.getInstance()
    private var newsType = "news_hot"

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        handler = NewsHandler(this)
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setOnRefreshListener(this)
        newsList = ArrayList()
        adapter = HotNewsAdapter(newsList)
        val linearLayoutManager = LinearLayoutManager(mContext)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = adapter
        adapter.setOnclickListener(this)
    }

    override fun loadData() {
        val bundle = arguments
        if (bundle != null) {
            val keyType = bundle.getString(Const.Key.KEY_TYPE)
            when(keyType) {
                NewsType.SOCIAL.toString() -> newsType = Const.News_Type.news_society
                NewsType.ENTERTAINMENT.toString() -> newsType = Const.News_Type.news_entertainment
                NewsType.TECH.toString() -> newsType = Const.News_Type.news_tech
                NewsType.CAR.toString() -> newsType = Const.News_Type.news_car
                NewsType.SPORT.toString() -> newsType = Const.News_Type.news_sport
                NewsType.FINANCE.toString() -> newsType = Const.News_Type.news_finance
                NewsType.MILITARY.toString() -> newsType = Const.News_Type.news_military
                NewsType.QA.toString() -> newsType = Const.News_Type.question_and_answer
            }
        }
        refreshLayout.isRefreshing = true
        executor.execute {
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.URL.BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(ApiService :: class.java)
            val map : LinkedHashMap<String, Any> = linkedMapOf(Const.Param.CATEGORY to newsType, Const.Param.COUNT to 30)
            apiService.getNews(map).enqueue(object : Callback<NewsResult> {
                override fun onFailure(call: Call<NewsResult>, t: Throwable) {
                    Log.d("onFailure", t.message)
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    message.data = bundle
                    message.arg1 = 1
                    handler.sendMessage(message)
                }

                override fun onResponse(call: Call<NewsResult>, response: Response<NewsResult>) {
                    newsList.clear()
                    Log.d("成功message", response.message())
                    val newsWrapper = response.body()!!
                    val data = newsWrapper.data
                    val newsList = ArrayList<News>()
                    val gson = Gson()
                    if (data != null) {
                        for (i in data) {
                            val content = i.content
                            val news : News = gson.fromJson(content, News :: class.java)
                            newsList.add(news)
                        }
                    }
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("content", newsList)
                    message.data = bundle
                    message.arg1 = 0
                    handler.sendMessage(message)
                }

            })
        }
    }

    private fun startRefresh() {
        refreshLayout.isRefreshing = true
    }

    override fun onRefresh() {
        startRefresh()
        loadData()
        finishRefresh()
    }

    private fun finishRefresh() {
        refreshLayout.isRefreshing = false
    }

    private fun showData(data: Bundle?) {
        if (data != null) {
            newsList = data.getParcelableArrayList("content")
            adapter.replaceAll(newsList)
            refreshLayout.isRefreshing = false
        }
    }

    private fun onFailure() {
        refreshLayout.isRefreshing = false
    }

    override fun onItemClick(news: News) {
        val intent = Intent(context, WebActivity:: class.java)
        intent.putExtra(Const.Key.START_TYPE, START_TYPE)
        intent.putExtra(Const.Key.KEY_NEWS, news)
        startActivity(intent)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    class NewsHandler(likeNewsFragment: NewsFragment) : Handler() {

        private var mWeakReference = WeakReference(likeNewsFragment)

        override fun handleMessage(msg: Message?) {
            val fragment = mWeakReference.get()
            if (fragment != null && msg != null && msg.data != null) {
                when(msg.arg1) {
                    0 -> fragment.showData(msg.data)
                    1 -> fragment.onFailure()
                }
            }

        }

    }

}