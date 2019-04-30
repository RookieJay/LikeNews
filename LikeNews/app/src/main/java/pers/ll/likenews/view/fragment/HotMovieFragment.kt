package pers.ll.likenews.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pers.ll.likenews.R
import pers.ll.likenews.api.Api
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Movie
import pers.ll.likenews.model.MovieResult
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.ToastUtils
import pers.ll.likenews.view.activity.MovieDetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotMovieFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, HotMovieAdapter.OnItemClickListener {

    private val Args_Success = 0
    private val Args_Failure = 1
    private val Args_Empty = 2

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var ivEmpty : ImageView
    private lateinit var tvEmpty : TextView
    private lateinit var mAdapter: HotMovieAdapter
    private var movieList = ArrayList<Movie>()
    private lateinit var movieHandler: Handler

    override fun setContentView(): Int {
        return R.layout.fragment_recycler_base_vertical
    }

    override fun initView() {
        movieHandler = MovieHandler(this)
        refreshLayout = findViewById(R.id.refreshLayout) as SwipeRefreshLayout
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        ivEmpty = findViewById(R.id.ivEmpty) as ImageView
        tvEmpty = findViewById(R.id.tvEmpty) as TextView
        val manager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = manager
        mAdapter = HotMovieAdapter(movieList, this)
        mRecyclerView.adapter = mAdapter
        setListener()
    }

    private fun setListener() {
        refreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
        ThreadPoolManager.getInstance().execute(Runnable {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MOVIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(Api :: class.java)
            val map : LinkedHashMap<String, Any> = linkedMapOf(Const.Param.start to 0, Const.Param.count to 15, Const.Param.city to "成都")
            apiService.getHotMovie(map).enqueue(object : Callback<MovieResult> {
                override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                    Log.d("onFailure", t.message)
                }

                override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                    val body = response.body()
                    if (body != null && body.movies != null) {
                        val list = body.movies as ArrayList<Movie>
                        if (list.size > 0) {
                            val bundle = Bundle()
                            bundle.putParcelableArrayList(Const.Key.KEY_MOVIE, list)
                            val msg = movieHandler.obtainMessage()
                            msg.what = Args_Success
                            msg.data = bundle
                            movieHandler.sendMessage(msg)
                        }
                    }
                }

            })
        })
    }

    override fun onRefresh() {
        loadData()
    }

    private fun startRefresh() {
        runOnUIThread(Runnable {
            if (refreshLayout.isRefreshing) {
                refreshLayout.isRefreshing = true
            }
        })
    }

    private fun finishRefresh() {
        runOnUIThread(Runnable {
            refreshLayout.isRefreshing = false
        })
    }

    private fun showData(list : ArrayList<Movie>) {
        mAdapter.replaceAll(list)
        finishRefresh()
    }

    private fun onFailure() {
        ToastUtils.showShort("网络请求失败")
        finishRefresh()
    }

    private fun showEmpty() {
        ivEmpty.visibility = View.VISIBLE
        tvEmpty.visibility = View.VISIBLE
        finishRefresh()
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(mContext, MovieDetailActivity().javaClass)
        intent.putExtra(Const.Key.KEY_MOVIE, movie)
        context.startActivity(intent)
    }

    class MovieHandler(hotMovieFragment: HotMovieFragment) : Handler() {

        private var fragment = hotMovieFragment

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null ) {
                val bundle = msg.data
                when(msg.what) {
                    fragment.Args_Success ->
                        if (bundle != null) {
                            val list = bundle.getParcelableArrayList<Movie>(Const.Key.KEY_MOVIE)
                            fragment.showData(list)
                        }
                    fragment.Args_Failure -> fragment.onFailure()
                    fragment.Args_Empty -> fragment.showEmpty()
                }
            }

        }
    }


}
