package pers.ll.likenews.view.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_music_play.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.include_music_play_toolbar.*
import okhttp3.MediaType
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.model.*
import pers.ll.likenews.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList


class MusicPlayActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    private val Args_Success = 0
    private val Args_Failure = 1
    private val Args_Empty = 2

//    private lateinit var music : Music
//    private lateinit var musicList : ArrayList<Music>
    private lateinit var music : XWMusic
    private lateinit var musicList : ArrayList<XWMusic>
    private lateinit var imageUtil : ImageUtil
    private lateinit var objectAnimator : ObjectAnimator
    private val STATE_PLAYING = 1 //正在播放
    private val STATE_PAUSE = 2 //暂停
    private val STATE_STOP = 3 //停止
    private var state : Int = 0
    private var hadDestroy = false
    private var player = MediaPlayer()
    private var isPlaying = true
    private var isRepeat = false
    private lateinit var tvMusicDuration : TextView
    private lateinit var tvCurTime : TextView
    private lateinit var seekBar : SeekBar
    private val handler = ProgressHandler(this)
    private lateinit var validateHandler : ValidateHandler
    private var executor = ThreadPoolManager.getInstance()
    private var isLrcShowing = false
    private var curPosition = -1
    private var bufferPercentage = 0
    private var totalDur = 0L

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_music_play)
        super.onCreate(savedInstanceState)
//        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        curPosition = intent.getIntExtra(Const.Key.KEY_POSITION, -1)
        musicList = intent.getParcelableArrayListExtra(Const.Key.KEY_MUSIC_LIST)
        initView()
        initPlayer()
        setListener()
    }

    @SuppressLint("ObjectAnimatorBinding")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {
        if (music.pic != null) {
            setImgAndBackground()
            /**
             *   （1）LinearInterpolator：动画从开始到结束，变化率是线性变化。
             *   （2）AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
             *   （3）DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
             *   （4）CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
             *   （5）AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
             */
            state = STATE_STOP
            tvCurTime = findViewById(R.id.tvCurTime)
            seekBar = findViewById(R.id.seekBar)
            tvMusicDuration = findViewById(R.id.tvMusicDuration)
            objectAnimator = ObjectAnimator.ofFloat(ivMusicPic, "rotation", 0f, 360f) //添加旋转动画，旋转中心默认为控件中点
            objectAnimator.duration = 20000 //设置动画时间
            objectAnimator.interpolator = LinearInterpolator() //动画时间线性渐变
            objectAnimator.repeatCount = ObjectAnimator.INFINITE
            objectAnimator.repeatMode = ObjectAnimator.RESTART
            hideLrc()
        }
        initToolbar()
    }

    private fun setImgAndBackground() {
        imageUtil = ImageUtil.getInstance()
        Glide.with(ivMusicPic.context)
            .load(music.pic)
            .centerCrop()
            .placeholder(ContextCompat.getDrawable(ivMusicPic.context, R.drawable.icon_music_placeholder))
            .error(ContextCompat.getDrawable(ivMusicPic.context, R.drawable.icon_music_placeholder))
            .into(ivMusicPic)
        executor.execute {
            val bitmap = imageUtil.url2BitMap(music.pic)
            if (bitmap != null) {
                //启用高斯模糊
                val blurBitmap = imageUtil.rsBlur(rlMusicPlay.context, bitmap, 24, 1f / 8f)
                //回到主线程
                MainHandler.getInstance().post {
                    rlMusicPlay.background = imageUtil.getDrawbleFormBitmap(rlMusicPlay.context, blurBitmap)
                }
            }
        }
    }

    private fun initToolbar() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        //跑马灯必须加
        barTitle.isSelected = true
        barTitle.isFocusable = true
        barTitle.isFocusableInTouchMode = true
//        barTitle.text = music.name
//        barSubTitle.text = music.singer
        barTitle.text = music.title
        barSubTitle.text = music.author
        //FLAG_LAYOUT_NO_LIMITS允许窗口扩展到屏幕之外。
//        // 但这样会把ToolBar顶到最上面去，这时候再给ToolBar设置一个MarginTop就好了。
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val toolbarParams = toolBarMusic.layoutParams as RelativeLayout.LayoutParams
        toolbarParams.setMargins(0, SystemUtils.getStatusBarHeight(resources), 0, 0)
        toolBarMusic.layoutParams = toolbarParams
        val rlParams = rlMusicCtrl.layoutParams as RelativeLayout.LayoutParams
        rlParams.setMargins(0, 0, 0, SystemUtils.getNavigationBarHeight(resources))
        rlMusicCtrl.layoutParams = rlParams

    }
//
//    private fun initPlayer() {
//        Log.d("-----MPA", "initPlayer")
//        validateHandler = ValidateHandler(this)
//        executor.execute(Runnable {
//            try {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl(Const.URL.BASE_URL_MUSIC)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                val apiService = retrofit.create(ApiService :: class.java)
//                val map = linkedMapOf("key" to 579621905, "id" to music.id, "br" to 999000)
//                val bundle = Bundle()
//                apiService.validateMusic(map).enqueue(object : Callback<MusicResult<String>>{
//                    @RequiresApi(Build.VERSION_CODES.KITKAT)
//                    override fun onFailure(call: Call<MusicResult<String>>, t: Throwable) {
//                        //现在用于接收的只是错误信息，如果解析错误，说明歌曲可以播放，在此做正确操作
//                        try {
//                            player.setDataSource(music.url)
//                            player.prepare()
//                            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
//                        } catch (e: Exception) {
//                        }
//                    }
//
//                    override fun onResponse(call: Call<MusicResult<String>>, response: Response<MusicResult<String>>) {
//                        val errorBody = response.errorBody()
//                        if (errorBody != null) {
//                            val json = errorBody.string()
//                            val body = Gson().fromJson<ErroBody>(json, ErroBody::class.java)
//                            if (body.code == 2333 || body.result == "ERROR") {
//                                val msg = validateHandler.obtainMessage()
//                                msg.arg1 = Args_Failure
//                                bundle.putString(Const.Key.KEY_MSG, "抱歉！\n由于版权或付费等原因，此歌曲暂时无法播放.\n请您谅解！")
//                                msg.data = bundle
//                                validateHandler.sendMessage(msg)
//                            } else {
//                                player.setDataSource(music.url)
//                                player.prepare()
//                                player.setAudioStreamType(AudioManager.STREAM_MUSIC)
//                            }
//                        }
//                    }
//
//                })
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        })
//    }

    private fun initPlayer() {
        Log.d("-----MPA", "initPlayer")
        validateHandler = ValidateHandler(this)
        executor.execute {
            try {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Const.URL.BASE_URL_MUSIC_XIAOWEI)
                    .build()
                val apiService = retrofit.create(ApiService :: class.java)
//                val map = linkedMapOf("key" to 523077333, "id" to music.title, "type" to "song")
                val bundle = Bundle()
                val msg = validateHandler.obtainMessage()
                apiService.validateMusicXW(music.url).enqueue(object : Callback<String>{
                    @RequiresApi(Build.VERSION_CODES.KITKAT)
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        bundle.putString(Const.Key.KEY_MSG, "抱歉！由于版权或付费等原因，此歌曲暂时无法播放。\n请您谅解！")
                        msg.arg1 = Args_Failure
                        msg.data = bundle
                        validateHandler.sendMessage(msg)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        val rawBody = response.raw().body()
                        //付费
                        if (rawBody != null && rawBody.contentType() == MediaType.parse("text/html;charset=utf8")) {
                            msg.arg1 = Args_Failure
                            bundle.putString(Const.Key.KEY_MSG, "抱歉！由于版权或付费等原因，此歌曲暂时无法播放。\n请您谅解！")
                            msg.data = bundle
                            validateHandler.sendMessage(msg)
                            return
                        }
                        val body = response.body()
                        if (body.isNullOrEmpty()) {
                            msg.arg1 = Args_Failure
                            bundle.putString(Const.Key.KEY_MSG, "未知错误！")
                            msg.data = bundle
                            validateHandler.sendMessage(msg)
                        } else {
                            player.setDataSource(music.url)
                            player.prepare()
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        }
                    }

                })

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun playMusic() {
        Log.d("-----MPA", "playMusic")
        when (state) {
            STATE_STOP -> {
                if (!player.isPlaying) {
                    Log.d("-----MPA", "startplayMusic")
                    player.start()
                    objectAnimator.start() //动画开始
                    state = STATE_PLAYING
                    Thread(SeekBarThread()).start() //线程开始
                    seekBar.max = player.duration
                    totalDur = player.duration.toLong()
                    val duration = TimeUtils.date2String(Date(totalDur), Const.DateFormat.MMSS)
                    tvMusicDuration.text = duration
                }
            }
            STATE_PAUSE -> {
                objectAnimator.resume() //动画重新开始
                state = STATE_PLAYING
            }
            STATE_PLAYING -> {
                objectAnimator.pause() //动画暂停
                state = STATE_PAUSE
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setListener() {
        barLeft.setOnClickListener {
            finish()
        }
        ivPause.setOnClickListener{
            if (isPlaying) {
                ivPause.setImageResource(R.drawable.ic_play_circle)
                if (objectAnimator != null) {
                    Log.d("-----MPA", "pauseplayMusic")
                    objectAnimator.pause() //动画暂停
                    state = STATE_PAUSE
                    player.pause()
                }
            } else{
                ivPause.setImageResource(R.drawable.ic_pause_circle)
                Log.d("-----MPA", "resumeplayMusic")
                if (objectAnimator != null) {
                    objectAnimator.resume()
                    state = STATE_PLAYING
                    player.start()
                }
            }
            isPlaying = !isPlaying
        }
        ivRecycle.setOnClickListener {
            if (!isRepeat) {
                ToastUtils.showShort("开启单曲循环")
                ivRecycle.setImageResource(R.drawable.ic_repeat_one_pink)
                player.isLooping = true
            } else {
                ivRecycle.setImageResource(R.drawable.ic_repeat_one)
                player.isLooping = false
            }
            isRepeat = !isRepeat
        }
        player.setOnErrorListener { mp, what, extra ->
            Log.d("-----MPA", "onError: what$what extra:$extra")
            false
        }
        player.setOnBufferingUpdateListener { mp, percent ->
                Log.i("percent", percent.toString())
                bufferPercentage = percent
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player.seekTo(progress)
                    val curTime = TimeUtils.date2String(Date(player.currentPosition.toLong()), Const.DateFormat.MMSS)
                    tvCurTime.text = curTime
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        player.setOnPreparedListener(this)
        rlMusicPlay.setOnClickListener {
            if (!isLrcShowing) {
                showLrc()
            } else {
                hideLrc()
            }
            isLrcShowing = !isLrcShowing
        }
        ivPrevious.setOnClickListener {
            switchToPrevious()

        }
        ivNext.setOnClickListener {
            switchToNext()
        }
    }

    private fun notifyAnimatorChange() {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun switchToPrevious() {
        if (curPosition == -1) {
            ToastUtils.showShort("未知错误")
            return
        }
        if (curPosition == 0) {
            ToastUtils.showShort("当前正在播放第一首歌")
            return
        }
        music = musicList[curPosition-1]
        curPosition -= 1
        switchMusic()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun switchToNext() {
        if (curPosition == -1) {
            ToastUtils.showShort("未知错误")
            return
        }
        if (curPosition == musicList.size - 1) {
            ToastUtils.showShort("当前正在播放最后一首歌")
            return
        }
        music = musicList[curPosition+1]
        curPosition += 1
        switchMusic()
    }

    private fun showLrc() {
        ivMusicPic.visibility = View.GONE
    }

    private fun hideLrc() {
        ivMusicPic.visibility = View.VISIBLE
    }

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    private fun switchMusic(music: Music) {
//        player.stop()
//        player.reset()
//        initView()
//        initPlayer()
//    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun switchMusic() {
        player.stop()
        player.reset()
//        setImgAndBackground()
//        initToolbar()
        initView()
        initPlayer()
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onPrepared(mp: MediaPlayer?) {
        playMusic()
    }

    private fun showErroSong(errorMsg: String?) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("错误")
            .setNegativeButton("确定") { _, _ -> finish()  }
            .setMessage(errorMsg)
            .setIcon(R.drawable.icon_music_placeholder)
            .create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun releaseMediaPlayer() {
        player.stop()
        hadDestroy = true
        player.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    private class ProgressHandler(activity : MusicPlayActivity) : Handler() {

        private val weakReference : WeakReference<MusicPlayActivity> = WeakReference(activity)

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val activity = weakReference.get()
            if (msg != null && activity != null) {
                val data = msg.data
                val curTime = data.getString("curTime")
                activity.seekBar.progress = msg.what
                activity.tvCurTime.text = curTime
                val total = activity.tvMusicDuration.text.toString()
                if (curTime == total) {
                    activity.switchToNext()
                }
            }
        }
    }

    inner class SeekBarThread : Runnable {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun run() {
            if (player != null && !hadDestroy) {
                val curTime = TimeUtils.date2String(Date(player.currentPosition.toLong()), Const.DateFormat.MMSS)
//                if (curTime == totalDur.toString()) {
//                    switchToNext()
//                    return
//                }
                val bundle  = Bundle()
                bundle.putString("curTime", curTime)
                val msg = handler.obtainMessage()
                msg.data = bundle
                msg.what = player.currentPosition
                handler.sendMessage(msg)
            }
            try {
                //每100毫秒更新一次
                handler.postDelayed(this, 80)
            } catch (e: Exception) {
            }
        }
    }

    class ValidateHandler(musicPlayActivity: MusicPlayActivity) : Handler() {

        private var activity = musicPlayActivity

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg != null) {
                val bundle = msg.data
                when(msg.arg1) {
                        activity.Args_Failure -> if (bundle != null) {
                            val errorMsg = bundle.getString(Const.Key.KEY_MSG)
                            activity.showErroSong(errorMsg)
                        }
                }
            }
        }

    }

}
