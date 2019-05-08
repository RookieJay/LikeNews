package pers.ll.likenews.view.fragment

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_recycler_base_vertical.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import pers.ll.likenews.model.MusicResult
import pers.ll.likenews.utils.DrawableUtil
import pers.ll.likenews.utils.ToastUtils
import pers.ll.likenews.utils.SystemUtils
import pers.ll.likenews.view.activity.MusicPlayActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, MusicListAdapter.OnItemClickListener {

    private val Args_Success = 0
    private val Args_Failure = 1
    private val Args_Empty = 2

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var adapter: MusicListAdapter
    private lateinit var handler : Handler
    private lateinit var musicList : ArrayList<Music>
    private lateinit var etSearch : EditText
    private lateinit var viewLine : View
    private var musicName = "春风十里"
    private lateinit var drawable : Drawable
    private lateinit var rlEmpty : RelativeLayout
    private val keyWords = arrayOf("古风", "林俊杰", "民谣", "防弹少年团", "英文", "张杰", "轻音乐", "rap", "粤语", "华语", "影视原声", "运动", "读书")

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        etSearch = findViewById(R.id.etSearch) as EditText
        viewLine = findViewById(R.id.view_line) as View
        etSearch.visibility = View.VISIBLE
        viewLine.visibility = View.VISIBLE
        drawable = ContextCompat.getDrawable(context ,R.drawable.icon_clear)!!
        rlEmpty = findViewById(R.id.rlEmpty) as RelativeLayout
        handler = MusicHandler(this)
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        musicList = ArrayList()
        adapter = MusicListAdapter(musicList)
        val linearLayoutManager = LinearLayoutManager(mContext)
        mRecyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        setLisener()
    }

    private fun setLisener() {
        refreshLayout.setOnRefreshListener(this)
        adapter.setOnItemClickListener(this)
        etSearch.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    etSearch.setCompoundDrawables(null, null, null, null)
                } else{
                    drawable.setBounds(0, 0 , 45, 45)
                    etSearch.setCompoundDrawables(null, null, drawable, null)
                }
            }
        })
        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SystemUtils.hideSoftKeyboard(etSearch)
                    val content = etSearch.text.toString()
                    if (content.isNullOrEmpty()) {
                        return true
                    }
                    startRefresh()
                    musicName = content
                    requestData()
                    return true
                }
                return false
            }
        })
        val drawableUtil = DrawableUtil(etSearch, object : DrawableUtil.OnDrawableListener{
            override fun onLeft(v: View?, left: Drawable?) {

            }

            override fun onRight(v: View?, right: Drawable?) {
                etSearch.setCompoundDrawables(null, null, null, null)
                //Editable.Factory.getInstance().newEditable("")
                etSearch.text.clear()
            }
        })
        etSearch.setTextIsSelectable(false)
        mRootView!!.setOnTouchListener(object : View.OnTouchListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                mRootView!!.isFocusable = true
                mRootView!!.isFocusableInTouchMode = true
                SystemUtils.hideSoftKeyboard(v)
                return false
            }
        })
    }

    private fun startRefresh() {
        runOnUIThread(Runnable {
            refreshLayout.isRefreshing = true
        })

    }

    override fun onRefresh() {
        if (etSearch.text.toString().isNotEmpty()) {
            musicName = etSearch.text.toString()
        } else {
            val random = (Math.random() * keyWords.size).toInt()
            musicName = keyWords[random]
            etSearch.hint = String.format("请输入您要搜索的关键词,如:%s", musicName)
        }
        requestData()
    }

    private fun finishRefresh() {
        runOnUIThread(Runnable {
            refreshLayout.isRefreshing = false
        })
    }

    override fun loadData() {
        startRefresh()
        requestData()
    }

    private fun requestData() {
        Thread(Runnable {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MUSIC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            val map : LinkedHashMap<String, Any> = linkedMapOf("key" to "579621905", "s" to musicName, "type" to "song",
                    "limit" to 30, "offset" to 0)
            apiService.searchMusic(map).enqueue(object : Callback<MusicResult<Music>> {
                override fun onFailure(call: Call<MusicResult<Music>>, t: Throwable) {
                    Log.d("Music->onFailure", t.message)
                    val message = handler.obtainMessage()
                    val bundle = Bundle()
                    message.data = bundle
                    message.arg1 = Args_Failure
                    handler.sendMessage(message)
                }

                override fun onResponse(call: Call<MusicResult<Music>>, response: Response<MusicResult<Music>>) {
                    musicList.clear()
                    Log.d("Music->成功message", response.message())
                    val result = response.body()
                    val message = handler.obtainMessage()
                    if (response.code() == 200 && result != null) {
                        val list = result.data as ArrayList<Music>
                        val bundle = Bundle()
                        bundle.putParcelableArrayList(Const.Key.KEY_MUSIC_LIST, list)
                        message.arg1 = Args_Success
                        message.data = bundle
                    } else {
                        message.arg1 = Args_Empty
                    }
                    handler.sendMessage(message)
                }
            })
        }).start()

    }

    private fun showData(data: Bundle?) {
        if (data != null) {
            rlEmpty.visibility = View.GONE
            musicList = data.getParcelableArrayList(Const.Key.KEY_MUSIC_LIST)
            adapter.replaceAll(musicList)
            finishRefresh()
        }
    }

    private fun onFailure() {
        ToastUtils.showShort("连接失败，请稍后重试")
        finishRefresh()
    }

    private fun showEmpty() {
        ToastUtils.showShort("没有找到相关歌曲哦，换下关键词试试呢")
        rlEmpty.visibility = View.VISIBLE
        adapter.replaceAll(ArrayList())
        adapter.notifyDataSetChanged()
        finishRefresh()
    }

    override fun onItemClick(music: Music, data: ArrayList<Music>, position: Int) {
        val intent = Intent(context, MusicPlayActivity:: class.java)
        intent.putExtra(Const.Key.KEY_POSITION, position)
        intent.putExtra(Const.Key.KEY_MUSIC, music)
        intent.putParcelableArrayListExtra(Const.Key.KEY_MUSIC_LIST, data)
        startActivity(intent)
    }

    class MusicHandler(musicListFragment: MusicListFragment) : Handler() {

        private var fragment = musicListFragment

        override fun handleMessage(msg: Message?) {
            if (msg != null && msg.data != null) {
                when(msg.arg1) {
                    fragment.Args_Success -> fragment.showData(msg.data)
                    fragment.Args_Failure -> fragment.onFailure()
                    fragment.Args_Empty -> fragment.showEmpty()
                }
            }
        }
    }



}