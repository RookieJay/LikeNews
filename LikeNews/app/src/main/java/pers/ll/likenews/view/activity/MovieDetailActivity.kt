package pers.ll.likenews.view.activity

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import pers.ll.likenews.R
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.base.BaseActivity
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Movie
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MovieDetailActivity : BaseActivity(), FilmerAdapter.OnItemClickListener {

    private val Args_Success = 0
    private val Args_Failure = 1
    private val Args_Empty = 2
    private val START_TYPE_MOVIE = 2
    private val START_TYPE_FILMER = 3
    private lateinit var movie: Movie
    private val imageUtil = ImageUtil.getInstance()
    private lateinit var handler : MovieHandler
    private val executor = ThreadPoolManager.getInstance()
    private lateinit var adapter : FilmerAdapter
    private lateinit var ivBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initView()
        initData()
        setListener()
    }

    private fun initView() {
//        ivBack = headLayout.findViewById(R.id.ivBack)
        handler = MovieHandler(this)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tvMovieName.isFocusable = true
        tvMovieName.isFocusableInTouchMode = true
        tvMovieName.isSelected = true
        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = FilmerAdapter(ArrayList(), this)
        recyclerView.adapter = adapter
        //沉浸式
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val params = toolBar.layoutParams as FrameLayout.LayoutParams
        params.setMargins(0, SystemUtils.getStatusBarHeight(resources), 0, 0)
        toolBar.layoutParams = params
    }

    private fun initData() {
        movie = intent.getParcelableExtra(Const.Key.KEY_MOVIE)
        tvMovieName.text = movie.title
        executor.execute(Runnable {
            val bitmap = imageUtil.url2BitMap(movie.images.large)
            if (bitmap != null) {
                //设置背景高斯模糊
                val overLay = imageUtil.rsBlur(headLayout.context, bitmap, 24, 1f/8f)
                runOnUiThread(Runnable {
                    headLayout.background = imageUtil.getDrawbleFormBitmap(headLayout.context, overLay)
                })
            }
        })
        Glide.with(ivMovie).load(movie.images.large).placeholder(ContextCompat.getDrawable(ivMovie.context, R.color.gray)).into(ivMovie)
        val rating = movie.rating.average.toFloat()
        if (rating == 0f) {
            rbRating.visibility = View.GONE
            tvRating.text = "暂无评分"
        } else {
            rbRating.rating = (rating / 2)
            tvRating.text = String.format("%s分", movie.rating.average.toString())
        }
        tvStars.text = String.format("%s人", movie.collect_count.toString())
        tvYear.text =  String.format("%s年上映", movie.year.toString())
        val type  = StringBuilder("")
        for (i in movie.genres) {
            if (movie.genres.last() == i) {
                type.append(i)
            } else {
                type.append(i).append("/")
            }
        }
        tvType.text = type.toString()
        loadData()
    }

    private fun loadData() {
        executor.execute(Runnable {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MOVIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val apiService = retrofit.create(ApiService :: class.java)
            val msg = handler.obtainMessage()
            val map : LinkedHashMap<String, Any> = linkedMapOf("apikey" to Const.Param.apikey)
            apiService.getMovie(movie.id, map).enqueue(object : Callback<Movie>{
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    msg.what = Args_Failure
                    handler.sendMessage(msg)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    val movie = response.body()
                    if (movie != null) {
                        val bundle = Bundle()
                        bundle.putParcelable(Const.Key.KEY_MOVIE, movie)
                        msg.what = Args_Success
                        msg.data = bundle
                    } else {
                        msg.what = Args_Empty
                    }
                    handler.sendMessage(msg)
                }
            })
        })
    }

    private fun setListener() {
        toolBar.setNavigationOnClickListener{
            onBackPressed()
        }
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset <= -headLayout.height / 2) {
                collapsingToolbarLayout.title = movie.title
                //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色 设置收缩展开toolbar字体颜色
                collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK)
                collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
            } else {
                collapsingToolbarLayout.title = ""
            }
        })
        tvUrl.setOnClickListener{
            val url = tvUrl.text.toString()
            if (url.isNotEmpty()) {
                val intent = Intent(this, WebActivity() :: class.java)
                intent.putExtra(Const.Key.START_TYPE, START_TYPE_MOVIE)
                intent.putExtra(Const.Key.KEY_MOVIE_URL, url)
                intent.putExtra(Const.Key.KEY_MOVIE, movie)
                startActivity(intent)
            }
        }
    }

    private fun showData(movie: Movie) {
        this.movie = movie
        val list = movie.countries
        val countries = StringBuilder()
        for (country in list) {
            if (country == list.last()) {
                countries.append(country)
            } else {
                countries.append(country).append("/")
            }
        }
        tvCountries.text = countries.toString()
        tvSummary.text = String.format("\u3000\u3000%s", movie.summary)
        tvUrl.text = movie.mobile_url
        val filmerList = ArrayList<Movie.Cast>()
        val directors = movie.directors
        if (directors != null && directors.size > 0) {
            for (director in directors) {
                val cast = Movie.Cast()
                cast.name = String.format("%s(导演)", director.name)
                val avatars = Movie.Cast.Avatars()
                avatars.large = director.avatars.large
                cast.avatars = avatars
                cast.alt = director.alt
                filmerList.add(cast)
            }
        }
        val casts = movie.casts
        if (casts != null && casts.size > 0) {
            for (cast in casts) {
                cast.name = String.format("%s(主演)", cast.name)
                filmerList.add(cast)
            }
        }
        adapter.replaceAll(filmerList)
    }

    private fun showEmpty() {
        ToastUtils.showShort("获取详情失败")
    }

    private fun showFailure() {
        ToastUtils.showShort("网络加载失败")
    }

    override fun onItemClick(filmer: Movie.Cast) {
        val intent = Intent(this, WebActivity() :: class.java)
        intent.putExtra(Const.Key.START_TYPE, START_TYPE_FILMER)
        intent.putExtra(Const.Key.KEY_FILMER, filmer)
        startActivity(intent)
    }

    class MovieHandler(movieDetailActivity: MovieDetailActivity) : Handler() {

        private var activity = movieDetailActivity

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                val bundle = msg.data
                val movie = bundle.getParcelable<Movie>(Const.Key.KEY_MOVIE)
                when(msg.what) {
                    activity.Args_Success -> if (movie != null){
                        activity.showData(movie)
                    } else {
                        activity.showEmpty()
                    }
                    activity.Args_Empty -> activity.showEmpty()
                    activity.Args_Failure -> activity.showFailure()
                }
            }
        }
    }
}
