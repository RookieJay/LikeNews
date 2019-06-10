package pers.ll.likenews.view.activity

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebViewClient
import pers.ll.likenews.R
import pers.ll.likenews.model.News
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Movie
import pers.ll.likenews.utils.ToastUtils
import pers.ll.likenews.utils.UIUtils
import android.content.Intent

class WebActivity : AppCompatActivity() {

    private lateinit var news : News
    private lateinit var ivBack : ImageView
    private lateinit var barTitle : TextView
    private var startType = 0
    private lateinit var movie : Movie
    private lateinit var url : String
    private lateinit var filmer : Movie.Cast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIUtils.setSameColorBar(true, window, resources)
        setContentView(R.layout.activity_news_detail)
        initView()
    }

    private fun initView() {
        ivBack = findViewById(R.id.barLeft)
        barTitle = findViewById(R.id.barTitle)
        //跑马灯必须加
        barTitle.isSelected = true
        barTitle.isFocusable = true
        barTitle.isFocusableInTouchMode = true
        ivBack.visibility = View.VISIBLE
        barRight.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_share_white_24dp))
        barRight.visibility = View.VISIBLE
        val intent = intent
        if (intent != null) {
            startType = intent.getIntExtra(Const.Key.START_TYPE, 0)
            when(startType) {
                0 -> {
                    ToastUtils.showShort("页面发生未知错误")
                    back()
                    return
                }
                1 -> {
                    news = intent.getParcelableExtra(Const.Key.KEY_NEWS)
                    barTitle.text = news.title
                    url = news.article_url.toString()
                }
                2 -> {
                    movie = intent.getParcelableExtra(Const.Key.KEY_MOVIE)
                    val url = intent.getStringExtra(Const.Key.KEY_MOVIE_URL)
                    barTitle.text = movie.title
                    this.url = movie.mobile_url
                }
                3 -> {
                    filmer = intent.getParcelableExtra(Const.Key.KEY_FILMER)
                    barTitle.text = filmer.name
                    this.url = filmer.alt
                }
            }

        }
        initWebView()
        setListener()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        //设置垂直滚动条
        webView.isVerticalScrollBarEnabled = true
        //支持javascript
        webView.settings.javaScriptEnabled = true
        // 设置可以支持缩放
        webView.settings.setSupportZoom(true)
        // 设置出现缩放工具
        webView.settings.builtInZoomControls = true
        //扩大比例的缩放
        webView.settings.useWideViewPort = true
        //自适应屏幕
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.settings.loadWithOverviewMode = true
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.contains("sina.cn")) {
                    view.loadUrl(url)
                    return true
                }

                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.url.toString().contains("sina.cn")) {
                        view.loadUrl(url)
                        return true
                    }
                }

                return false
            }

        }
        webView.loadUrl(url)
    }

    private fun back() {
        finish()
    }

    private fun setListener() {
        ivBack.setOnClickListener { back() }
        barRight.setOnClickListener {
            val textIntent = Intent(Intent.ACTION_SEND)
            textIntent.type = "text/plain"
            textIntent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(textIntent, "分享"))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}