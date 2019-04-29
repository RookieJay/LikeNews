package pers.ll.likenews.view.fragment

import android.webkit.WebSettings
import android.webkit.WebViewClient
import pers.ll.likenews.R
import pers.ll.likenews.model.News
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_news_detail.*
import pers.ll.likenews.ui.Html5Webview
import pers.ll.likenews.utils.ToastUtils


class NewsDetailActivity : AppCompatActivity() {

    private lateinit var news : News
    private lateinit var Html5Webview: WebView
    private lateinit var ivBack : ImageView
    private lateinit var barTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val intent = intent
        if (intent != null) {
            news = intent.getParcelableExtra("news")
        }
        if (news == null) {
            ToastUtils.showShort("网页地址为空")
            back()
        }
        barTitle.text = news.title
        initWebView()
        setListener()

    }

    private fun initWebView() {
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
                    view.loadUrl(news.article_url)
                    return true
                }

                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.url.toString().contains("sina.cn")) {
                        view.loadUrl(news.article_url)
                        return true
                    }
                }

                return false
            }

        }
        webView.loadUrl(news.article_url)
    }

    private fun back() {
        finish()
    }

    private fun setListener() {
        ivBack.setOnClickListener(View.OnClickListener {
            v -> back()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}