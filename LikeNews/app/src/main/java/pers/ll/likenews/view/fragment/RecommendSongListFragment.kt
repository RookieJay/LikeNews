package pers.ll.likenews.view.fragment

import android.app.Activity
import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wuyr.litepager.LitePager
import kotlinx.android.synthetic.main.fragment_normal_song_list.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.MusicResult
import pers.ll.likenews.model.SongList
import pers.ll.likenews.ui.MyLitePager
import pers.ll.likenews.utils.*
import pers.ll.likenews.view.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RecommendSongListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, MainActivity.MyTouchListener {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var parentFrag : LikeMusicFragment
    private lateinit var litePager : LitePager
    private lateinit var mRecyclerView : RecyclerView
    private var executor = ThreadPoolManager.getInstance()
    private var mainThread = MainHandler.getInstance()
    private lateinit var mAdapter : SongListAdapter
    private lateinit var ivEmpty : ImageView
    private var headList =  ArrayList<SongList>()
    private lateinit var ivHeader1 : ImageView
    private lateinit var ivHeader2 : ImageView
    private lateinit var ivHeader3 : ImageView
    private lateinit var curActivity: MainActivity

    override fun setContentView(): Int {
        return R.layout.fragment_recommend_song_list
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        curActivity = context as MainActivity
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setColorSchemeColors(
                UIUtils.getColor(context, android.R.color.holo_blue_light), UIUtils.getColor(context,android.R.color.holo_red_light),
                UIUtils.getColor(context,android.R.color.holo_green_light), UIUtils.getColor(context,android.R.color.holo_orange_light))
        ivEmpty = findViewById(R.id.ivEmpty) as ImageView
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        ivHeader1 = findViewById(R.id.ivHeader1) as ImageView
        ivHeader2 = findViewById(R.id.ivHeader2) as ImageView
        ivHeader3 = findViewById(R.id.ivHeader3) as ImageView
        mAdapter = SongListAdapter(context)
        parentFrag = parentFragment as LikeMusicFragment
        litePager = findViewById(R.id.litePager) as LitePager
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.isNestedScrollingEnabled = false
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
            val map = linkedMapOf("cat" to "全部", "pageSize" to 21, "page" to 0)
            apiService.hotSongList(map).enqueue(object : Callback<MusicResult<SongList>>{
                override fun onFailure(call: Call<MusicResult<SongList>>, t: Throwable) {
                    Log.d("连接失败", "连接失败"+t.message)
                    ToastUtils.showShort("连接失败"+t.message)
                }

                override fun onResponse(call: Call<MusicResult<SongList>>, response: Response<MusicResult<SongList>>) {
                    val result = response.body()
                    val songList = result?.data as ArrayList<SongList>
                    if (songList.isNotEmpty()) {
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

    private fun showData(songList: ArrayList<SongList>) {
        mainThread.post {
            headList.clear()
            headList.addAll(songList.subList(0, 3)) //subList是左闭右开区间
            showHeader(headList)
            val list = songList.subList(3, songList.size)
            mAdapter.replaceAll(list)
            mAdapter.notifyDataSetChanged()
            finishRefresh()
        }
    }

    private fun showHeader(headList: ArrayList<SongList>) {
//        if (litePager.getChildAt(0) != null) {
//            litePager.removeAllViews()
//        }
//        for ((i, songList) in headList.withIndex()) {
//            val layout = layoutInflater.inflate(R.layout.item_song_list,  litePager, false)
//            val iv = layout.findViewById<RoundCornerImageView>(R.id.ivSongList)
//            val tv = layout.findViewById<TextView>(R.id.tvSongListName)
//            Glide.with(context).load(songList.coverImgUrl).placeholder(R.mipmap.icon_placeholder).into(iv)
//            tv.text = songList.name
//            litePager.addViews(layout)
////            val imageView = ImageView(context)
////            imageView.layoutParams = ViewGroup.LayoutParams(UIUtils.dp2px(mContext, 150f), UIUtils.dp2px(mContext, 160f))
////            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
////            Glide.with(context).load(songList.coverImgUrl).placeholder(R.mipmap.icon_placeholder).into(imageView)
////            litePager.addView(imageView)
//        }
        Glide.with(mContext).load(headList[0].coverImgUrl).placeholder(R.mipmap.icon_placeholder).into(ivHeader1)
        Glide.with(mContext).load(headList[1].coverImgUrl).placeholder(R.mipmap.icon_placeholder).into(ivHeader2)
        Glide.with(mContext).load(headList[2].coverImgUrl).placeholder(R.mipmap.icon_placeholder).into(ivHeader3)
    }

    private fun setListener() {
        curActivity.registerMyTouchListener(this)   //注册触摸事件
        refreshLayout.setOnRefreshListener(this)
//        litePager.setOnItemSelectedListener {
//            ToastUtils.showShort("点击了item")
//        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //在litePager范围类
        val isIntercept = UIUtils.inRangeOfView(litePager, event)
         if(isIntercept) {
             parentFrag.setCanScroll(false)
        } else {
             parentFrag.setCanScroll(true)
         }
        return isIntercept
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        curActivity.unRegisterMyTouchListener(this)
    }
}
