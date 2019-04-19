package pers.ll.likenews.view.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pers.ll.likenews.R
import pers.ll.likenews.api.Api
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.base.Const
import pers.ll.likenews.model.News
import pers.ll.likenews.utils.ToastUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotNewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener{

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var adapter: HotNewsAdapter
    private lateinit var newsList : ArrayList<News>


    override fun setContentView(): Int {
        return R.layout.fragment_news_hot
    }

    override fun initView() {
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
//        val mockData = MockData(mContext)
        newsList = ArrayList()
//        for (i in 1..10) {
//            val news = News()
////            news.title = mockData.getJSONData("title").toString()
//            news.title = "今日头条今日头条今日头条今日头条今日头条"
//            news.publish_time = 20190419
//            news.source = "中国新闻网"
//            news.comment_count = 238
//            newsList.add(news)
//        }
        Thread(Runnable {
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(Api :: class.java)
            //写到这里
        }).start()
        adapter = HotNewsAdapter(newsList)
        val linearLayoutManager = LinearLayoutManager(mContext)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = adapter
    }

     fun startRefresh() {
         refreshLayout.isRefreshing = true
         ToastUtils.showShort("开始刷新")
    }

    override fun onRefresh() {
        startRefresh()
        ToastUtils.showShort("正在刷新")
        newsList.clear()
//        for (i in 1..10) {
//            val news = News()
//            news.title = "替换的今日头条替换的今日头条替换的今日头条"
//            news.publish_time = 20190419
//            news.source = "替换的中国新闻网"
//            news.comment_count = 238
//            newsList.add(news)
//        }
        loadData()
        adapter.replaceAll(newsList)
        finishRefresh()
    }

     fun finishRefresh() {
         refreshLayout.isRefreshing = false
         ToastUtils.showShort("结束刷新")
    }
}