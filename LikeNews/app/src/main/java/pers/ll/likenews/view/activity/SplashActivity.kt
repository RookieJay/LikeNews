package pers.ll.likenews.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.jinrishici.sdk.android.JinrishiciClient
import com.jinrishici.sdk.android.listener.JinrishiciCallback
import com.jinrishici.sdk.android.model.JinrishiciRuntimeException
import com.jinrishici.sdk.android.model.PoetySentence
import kotlinx.android.synthetic.main.activity_splash.*
import pers.ll.likenews.utils.*
import java.lang.ref.WeakReference
import android.os.Build
import android.view.View
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import pers.ll.likenews.R
import java.util.*
import java.util.function.Consumer


class SplashActivity : AppCompatActivity() {

    private var mHandler = DelayHandler(this)
    private var executor = ThreadPoolManager.getInstance()
    private var imageUtil = ImageUtil.getInstance()
    private var isThreadStop = false
    private lateinit var countDownRunnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIUtils.hideStatusAndNavBar(window)
        setContentView(R.layout.activity_splash)
        requestPermission()
        initView()
        countDown()
        setListener()
    }

    @SuppressLint("CheckResult")
    private fun requestPermission() {
        val rxPermission = RxPermissions(this)
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
            .subscribe {
                when {
                    it.granted -> {
                        Log.i(SplashActivity().javaClass.name, "WRITE_EXTERNAL_STORAGE GRANTED")
                    }
                    it.shouldShowRequestPermissionRationale -> {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    }
                    else -> // 用户拒绝了该权限，并且选中『不再询问』
                        ToastUtils.showShort("拒绝权限")
                }
            }

    }

    private fun initView() {
//        flSplash.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
//        val layoutParams = tv_skip.layoutParams as RelativeLayout.LayoutParams
//        layoutParams.setMargins(0, SystemUtils.getStatusBarHeight(resources) , 0, 0)
//        val lineParams = lineVertical.layoutParams as RelativeLayout.LayoutParams
//        lineParams.setMargins(0, 0, 0, SystemUtils.getNavigationBarHeight(resources))
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    private fun countDown() {
        if (isThreadStop) {
            return
        }
        countDownRunnable = Runnable {
            for (i in 5 downTo 0) {
                if (isThreadStop) {
                    break
                }
                if (i == 0) {
                    isThreadStop = true
                    MainHandler.getInstance().post {
                        startActivity(Intent(applicationContext, MainActivity().javaClass))
                        finish()
                    }
                } else {
                    MainHandler.getInstance().post {
                        tv_skip.text = String.format("跳过%s秒", i.toString())
                    }
                }
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        executor.execute(countDownRunnable)
        val client = JinrishiciClient()
        client.getOneSentenceBackground(object : JinrishiciCallback {
            override fun done(poetySentence: PoetySentence?) {
                if (poetySentence != null && poetySentence.data != null) {
                    val content = poetySentence.data.content
                    val sentences = content.split("，")
                    val sentence1 = filterMark(sentences[0])
                    val sentence2 = sentences[1].replace("。", "")
                    tvSentence1.text = sentence1
                    tvSentence2.text = sentence2
                }
            }

            override fun error(e: JinrishiciRuntimeException?) {
                Log.d("poetry return error", e?.code.toString() + e?.message)
            }

        })
    }


    private fun filterMark(str : String) : String{
        val marks = "/[\\u3002|\\uff1f|\\uff01|\\uff0c|\\u3001|\\uff1b|\\uff1a|\\u201c|\\u201d|\\u2018|\\u2019|\\uff08|\\uff09|\\u300a|\\u300b|\\u3008|\\u3009|\\u3010|\\u3011|\\u300e|\\u300f|\\u300c|\\u300d|\\ufe43|\\ufe44|\\u3014|\\u3015|\\u2026|\\u2014|\\uff5e|\\ufe4f|\\uffe5]/"
        return str.replace(marks, "")
    }

    private fun setListener() {
        tv_skip.setOnClickListener {
            startActivity(Intent(this, MainActivity().javaClass))
            executor.remove(countDownRunnable)
            isThreadStop = true
            finish()
        }
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
        executor.remove(countDownRunnable)
        isThreadStop = true
        super.onDestroy()
    }

    class DelayHandler(splashActivity: SplashActivity) : Handler() {

        private val weakReference = WeakReference<SplashActivity>(splashActivity)

        override fun handleMessage(msg: Message) {
            val activity = weakReference.get()
            if (activity != null) {
                when (msg.what) {
                    1 -> if (msg.arg1 == 0) {
                        activity.startActivity(Intent(activity.applicationContext, MainActivity().javaClass))
                        Log.d("SP>>>", "DelayHandler执行了")
                        activity.finish()
                    } else {
                        activity.tv_skip.text = String.format("跳过%s秒", msg.arg1.toString())
                    }
                }
            }
        }
    }
}
