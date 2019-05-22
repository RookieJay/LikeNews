package pers.ll.likenews.view.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.wuyr.litepager.LitePager
import kotlinx.android.synthetic.main.fragment_normal_song_list.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.MusicResult
import pers.ll.likenews.model.SongList
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RecommendSongListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener  {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var parentFrag : LikeMusicFragment
    private lateinit var litePager: LitePager
    private lateinit var mRecyclerView : RecyclerView
    private var executor = ThreadPoolManager.getInstance()
    private var mainThread = MainHandler.getInstance()
    private lateinit var mAdapter : SongListAdapter
    private lateinit var ivEmpty : ImageView

    override fun setContentView(): Int {
        return R.layout.fragment_recommend_song_list
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setColorSchemeColors(
                UIUtils.getColor(context, android.R.color.holo_blue_light), UIUtils.getColor(context,android.R.color.holo_red_light),
                UIUtils.getColor(context,android.R.color.holo_green_light), UIUtils.getColor(context,android.R.color.holo_orange_light))
        ivEmpty = findViewById(R.id.ivEmpty) as ImageView
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        mAdapter = SongListAdapter(context)
        parentFrag = parentFragment as LikeMusicFragment
        litePager = findViewById(R.id.litePager) as LitePager
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        mRecyclerView.adapter = mAdapter
        setListener()
    }

    override fun loadData() {
        executor.execute {
            startRefresh()
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MUSIC_NEW)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            val map = linkedMapOf("cat" to "全部", "pageSize" to 20, "page" to 0)
            apiService.hotSongList(map).enqueue(object : Callback<MusicResult<SongList>>{
                override fun onFailure(call: Call<MusicResult<SongList>>, t: Throwable) {
                    Log.d("连接失败", "连接失败"+t.message)
                    ToastUtils.showShort("连接失败"+t.message)
                }

                override fun onResponse(call: Call<MusicResult<SongList>>, response: Response<MusicResult<SongList>>) {
                    Log.d("1111", "1111")
                    val result = response.body()
                    val songList = result?.data
                    if (songList != null && songList.isNotEmpty()) {
                        showData(songList)
                    } else {
                        showEmpty()
                    }
                }

            })
        }
    }

    override fun onRefresh() {
        loadData()
    }

    private fun startRefresh() {
        mainThread.post {
            if (!refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = true
            }
        }
    }

    private fun finishRefresh() {
        mainThread.post {
            if (refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = false
            }
        }
    }

    private fun showEmpty() {
        ivEmpty.visibility = View.VISIBLE
        mAdapter.clear()
        mAdapter.notifyDataSetChanged()
        finishRefresh()
    }

    private fun showData(songList: List<SongList>) {
        mainThread.post {
            mAdapter.addAll(songList)
            mAdapter.notifyDataSetChanged()
            finishRefresh()
        }
    }

    private fun setListener() {
        //注册触摸事件
//        parentFrag.registerMyTouchListener(this)
    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        //在litePager范围类
//        return UIUtils.inRangeOfView(litePager, event)
//    }

    override fun onDestroy() {
        super.onDestroy()
//        parentFrag.unRegisterMyTouchListener(this)
    }
}
