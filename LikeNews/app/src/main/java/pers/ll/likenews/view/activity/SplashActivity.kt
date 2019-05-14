package pers.ll.likenews.view.activity

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
import pers.ll.likenews.R
import kotlinx.android.synthetic.main.activity_splash.*
import pers.ll.likenews.consts.Const
import pers.ll.likenews.utils.ImageUtil
import pers.ll.likenews.utils.MainHandler
import pers.ll.likenews.utils.ThreadPoolManager
import java.lang.ref.WeakReference

class SplashActivity : AppCompatActivity() {

    private var mHandler = DelayHandler(this)
    private var executor = ThreadPoolManager.getInstance()
    private var imageUtil = ImageUtil.getInstance()
    private lateinit var countDownRunnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        countDown()
        setListener()
    }

    private fun initView() {
//        flSplash.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    private fun countDown() {
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
        countDownRunnable = Runnable {
            for (i in 5 downTo 0) {
                val msg = mHandler.obtainMessage()
                msg.what = 1
                msg.arg1 = i
                mHandler.sendMessage(msg)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
//        executor.execute(countDownRunnable)
        Thread(countDownRunnable).start()
    }


    private fun filterMark(str : String) : String{
        val marks = "/[\\u3002|\\uff1f|\\uff01|\\uff0c|\\u3001|\\uff1b|\\uff1a|\\u201c|\\u201d|\\u2018|\\u2019|\\uff08|\\uff09|\\u300a|\\u300b|\\u3008|\\u3009|\\u3010|\\u3011|\\u300e|\\u300f|\\u300c|\\u300d|\\ufe43|\\ufe44|\\u3014|\\u3015|\\u2026|\\u2014|\\uff5e|\\ufe4f|\\uffe5]/"
        return str.replace(marks, "")
    }

    private fun setListener() {
        tv_skip.setOnClickListener {
            startActivity(Intent(this, MainActivity().javaClass))
            finish()
        }
    }

    override fun onDestroy() {
        mHandler.removeCallbacksAndMessages(null)
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
