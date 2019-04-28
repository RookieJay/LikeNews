package pers.ll.likenews.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_recycler_base_vertical.*
import pers.ll.likenews.R
import pers.ll.likenews.api.Api
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import pers.ll.likenews.model.MusicResult
import pers.ll.likenews.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, MusicListAdapter.OnItemClickListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var adapter: MusicListAdapter
    private lateinit var handler : Handler
    private lateinit var musicList : ArrayList<Music>
    private lateinit var etSearch : EditText
    private lateinit var viewLine : View

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        etSearch = findViewById(R.id.etSearch) as EditText
        viewLine = findViewById(R.id.view_line) as View
        etSearch.visibility = View.VISIBLE
        viewLine.visibility = View.VISIBLE
        handler = MusicHandler(this)
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        adapter = MusicListAdapter(ArrayList())
        val linearLayoutManager = LinearLayoutManager(mContext)
        mRecyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    fun startRefresh() {
        refreshLayout.isRefreshing = true
    }

    override fun onRefresh() {
        loadData()
    }

    fun finishRefresh() {
        refreshLayout.isRefreshing = false
    }

    override fun loadData() {
        startRefresh()
        musicList = ArrayList()
        Thread(Runnable {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MUSIC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(Api :: class.java)
                val map : LinkedHashMap<String, Any> = linkedMapOf("key" to "579621905", "s" to "春风十里", "type" to "song",
                    "limit" to 100, "offset" to 0)
            apiService.searchMusic(map).enqueue(object : Callback<MusicResult<Music>> {
                override fun onFailure(call: Call<MusicResult<Music>>, t: Throwable) {
                    Log.d("Music->onFailure", t.message)
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    message.data = bundle
                    message.arg1 = 1
                    handler.sendMessage(message)
                }

                override fun onResponse(call: Call<MusicResult<Music>>, response: Response<MusicResult<Music>>) {
                    musicList.clear()
                    Log.d("Music->成功message", response.message())
                    val result = response.body()!!
                    val list = result.data as ArrayList<Music>
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(Const.Key.KEY_MUSIC_LIST, list)
                    message.data = bundle
                    message.arg1 = 0
                    handler.sendMessage(message)
                }
            })
        }).start()


    }

    private fun showData(data: Bundle?) {
        if (data != null) {
            musicList = data.getParcelableArrayList(Const.Key.KEY_MUSIC_LIST)
            adapter.replaceAll(musicList)
            finishRefresh()
        }
    }

    private fun onFailure() {
        ToastUtils.showShort("连接失败，请稍后重试")
        finishRefresh()
    }

    override fun onItemClick(music: Music) {
        val intent = Intent(context, MusicPlayActivity :: class.java)
        intent.putExtra(Const.Key.KEY_MUSIC, music)
        startActivity(intent)
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