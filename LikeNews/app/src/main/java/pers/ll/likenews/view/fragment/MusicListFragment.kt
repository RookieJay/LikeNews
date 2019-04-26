package pers.ll.likenews.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recycler_base_vertical.*
import pers.ll.likenews.R
import pers.ll.likenews.api.Api
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import pers.ll.likenews.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var adapter: MusicListAdapter
    private lateinit var handler : Handler
    private lateinit var musicList : ArrayList<Music>

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        handler = MusicHandler(this)
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout

    }

    override fun onRefresh() {

    }

    override fun loadData() {
        refreshLayout.isRefreshing = true
        musicList = ArrayList()
        Thread(Runnable {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MUSIC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(Api :: class.java)
            val map : LinkedHashMap<String, Any> = linkedMapOf("key" to "579621905", "s" to "春风十里", "type" to "song",
                    "limit" to 100, "offset" to 0)
            apiService.searchMusic(map).enqueue(object : Callback<Result<Music>> {
                override fun onFailure(call: Call<Result<Music>>, t: Throwable) {
                    Log.d("onFailure", t.message)
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    message.data = bundle
                    message.arg1 = 1
                    handler.sendMessage(message)
                }

                override fun onResponse(call: Call<Result<Music>>, response: Response<Result<Music>>) {
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
        }).start()
    }

    private fun showData(data: Bundle?) {
        if (data != null) {
            musicList = data.getParcelableArrayList("musics");
            adapter = MusicListAdapter()
            recyclerView.adapter = adapter
        }
    }

    private fun onFailure() {

    }

    class MusicHandler(musicListFragment: MusicListFragment) : Handler() {

        private var fragment = musicListFragment

        override fun handleMessage(msg: Message?) {
            if (msg != null && msg.data != null) {
                when(msg.arg1) {
                    0 -> fragment.showData(msg.data)
                    1 -> fragment.onFailure()
                }
            }
        }
    }




}