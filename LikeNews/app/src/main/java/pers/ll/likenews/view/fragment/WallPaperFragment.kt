package pers.ll.likenews.view.fragment

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Wallpaper
import pers.ll.likenews.model.WallpaperResult
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WallPaperFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, TextWatcher {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var rlEmpty : RelativeLayout
    private lateinit var ivEmpty : ImageView
    private lateinit var tvEmpty : TextView
    private lateinit var etSearch : EditText
    private lateinit var drawable : Drawable
    private lateinit var mAdapter : WallpaperAdapter

    private var mExecutor = ThreadPoolManager.getInstance()
    private var mMainHandler = MainHandler.getInstance()

    private lateinit var mKeyWords : String

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        refreshLayout.setColorSchemeColors(
            UIUtils.getColor(context, android.R.color.holo_blue_light), UIUtils.getColor(context,android.R.color.holo_red_light),
            UIUtils.getColor(context,android.R.color.holo_green_light), UIUtils.getColor(context,android.R.color.holo_orange_light))
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        mAdapter = WallpaperAdapter(mContext)
        val layoutManager = GridLayoutManager(mContext, 2)
        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.adapter = mAdapter
        rlEmpty = findViewById(R.id.rlEmpty) as RelativeLayout
        ivEmpty = findViewById(R.id.ivEmpty) as ImageView
        tvEmpty = findViewById(R.id.tvEmpty) as TextView
        etSearch = findViewById(R.id.etSearch) as EditText
        drawable = ContextCompat.getDrawable(context ,R.drawable.icon_clear) as Drawable
        val viewLine = findViewById(R.id.view_line) as View
        etSearch.visibility = View.VISIBLE
        viewLine.visibility = View.VISIBLE
        initData()
        setListener()
    }

    private fun initData() {
        mKeyWords = "风景"
        etSearch.hint = context.resources.getString(R.string.hint_search_wallpaper)
    }

    private fun setListener() {
        refreshLayout.setOnRefreshListener(this)
        etSearch.addTextChangedListener(this)
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
        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SystemUtils.hideSoftKeyboard(etSearch)
                    val content = etSearch.text.toString()
                    if (content.isNullOrEmpty()) {
                        return true
                    }
                    startRefresh()
                    loadData()
                    return true
                }
                return false
            }
        })
        mRootView?.setOnClickListener {
            SystemUtils.hideSoftKeyboard(etSearch)
        }
    }

    override fun loadData() {
        startRefresh()
        mExecutor.execute {
            val retrofit = Retrofit.Builder()
                .baseUrl(Const.URL.BASE_URL_WALLPAPER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(ApiService :: class.java)
            val map = linkedMapOf("kw" to mKeyWords, "start" to 0, "count" to 99)
            apiService.searchWallpaper(map).enqueue(object : Callback<WallpaperResult>{
                override fun onFailure(call: Call<WallpaperResult>, t: Throwable) {
                    showError()
                }

                override fun onResponse(call: Call<WallpaperResult>, response: Response<WallpaperResult>) {
                    val body =  response.body()
                    if (body != null) {
                        val data = body.data as ArrayList<Wallpaper>
                        if (data.size == 0) {
                            showEmpty()
                            return
                        }
                        showData(data)
                    }
                }
            })
        }
    }

    private fun showError() {
        ToastUtils.showShort("连接失败")
        finishRefresh()
    }

    private fun showData(data: ArrayList<Wallpaper>) {
        mMainHandler.post {
            rlEmpty.visibility = View.GONE
            mAdapter.replaceAll(data)
            mAdapter.notifyDataSetChanged()
            finishRefresh()
        }
    }

    private fun showEmpty() {
        mMainHandler.post {
            if (mAdapter.data != null && mAdapter.data.size > 0) {
                mAdapter.clear()
                mAdapter.notifyDataSetChanged()
            }
            rlEmpty.visibility = View.VISIBLE
            tvEmpty.visibility = View.VISIBLE
            ivEmpty.visibility = View.VISIBLE
            finishRefresh()
        }
    }

    override fun onRefresh() {
        loadData()
    }

    private fun startRefresh() {
        mMainHandler.post {
            if (!refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = true
            }
        }
    }

    private fun finishRefresh() {
        mMainHandler.post {
            if (refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = false
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        mKeyWords = s.toString()
        if (s.isNullOrEmpty()) {
            etSearch.setCompoundDrawables(null, null, null, null)
        } else{
            drawable.setBounds(0, 0 , 45, 45)
            etSearch.setCompoundDrawables(null, null, drawable, null)
        }
    }

}