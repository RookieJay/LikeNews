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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_music_play.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.include_music_play_toolbar.*
import pers.ll.likenews.api.ApiService
import pers.ll.likenews.model.ErroBody
import pers.ll.likenews.model.MusicResult
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

    private lateinit var music : Music
    private lateinit var musicList : ArrayList<Music>
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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_music_play)
        super.onCreate(savedInstanceState)
        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        curPosition = intent.getIntExtra(Const.Key.KEY_POSITION, -1)
        musicList = intent.getParcelableArrayListExtra(Const.Key.KEY_MUSIC_LIST)
//        for ((index, element) in musicList.withIndex()) {
//            if (music.equals(element)) {
//                curPosition = index
//            }
//        }
        initView()
        initPlayer()
        setListener()
    }

    @SuppressLint("ObjectAnimatorBinding")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {
        initToolbar()
        if (music.pic != null) {
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
                    val blurBitmap = imageUtil.rsBlur(ivBg.context, bitmap, 24, 1f / 8f)
                    //回到主线程
                    MainHandler.getInstance().post {
                        //                        ivBg.setImageDrawable(imageUtil.getDrawbleFormBitmap(ivBg.context, blurBitmap))
                        ivBg.background = imageUtil.getDrawbleFormBitmap(ivBg.context, blurBitmap)
                    }
                }
            }
            /**
             *   （1）LinearInterpolator：动画从开始到结束，变化率是线性变化。
             *   （2）AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
             *   （3）DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
             *   （4）CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
             *   （5）AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
             */
            state = STATE_STOP
            objectAnimator = ObjectAnimator.ofFloat(ivMusicPic, "rotation", 0f, 360f) //添加旋转动画，旋转中心默认为控件中点
            objectAnimator.duration = 20000 //设置动画时间
            objectAnimator.interpolator = LinearInterpolator() //动画时间线性渐变
            objectAnimator.repeatCount = ObjectAnimator.INFINITE
            objectAnimator.repeatMode = ObjectAnimator.RESTART
            tvMusicDuration = findViewById(R.id.tvMusicDuration)
            tvCurTime = findViewById(R.id.tvCurTime)
            seekBar = findViewById(R.id.seekBar)
            hideLrc()
        }
    }

    private fun initToolbar() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        //跑马灯必须加
        barTitle.isSelected = true
        barTitle.isFocusable = true
        barTitle.isFocusableInTouchMode = true
        barTitle.text = music.name
        barSubTitle.text = music.singer
        //FLAG_LAYOUT_NO_LIMITS允许窗口扩展到屏幕之外。
//        // 但这样会把ToolBar顶到最上面去，这时候再给ToolBar设置一个MarginTop就好了。
//        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//        val params = toolBarMusic.layoutParams as RelativeLayout.LayoutParams
//        params.setMargins(0, SystemUtils.getStatusBarHeight(resources), 0, 0)
//        toolBarMusic.layoutParams = params

    }

    private fun initPlayer() {
        Log.d("-----MPA", "initPlayer")
        validateHandler = ValidateHandler(this)
        executor.execute(Runnable {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Const.URL.BASE_URL_MUSIC)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiService = retrofit.create(ApiService :: class.java)
                val map = linkedMapOf("key" to 579621905, "id" to music.id, "br" to 999000)
                val bundle = Bundle()
                apiService.validateMusic(map).enqueue(object : Callback<MusicResult<String>>{
                    @RequiresApi(Build.VERSION_CODES.KITKAT)
                    override fun onFailure(call: Call<MusicResult<String>>, t: Throwable) {
                        //现在用于接收的只是错误信息，如果解析错误，说明歌曲可以播放，在此做正确操作
                        try {
                            player.setDataSource(music.url)
                            player.prepare()
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        } catch (e: Exception) {
                        }
                    }

                    override fun onResponse(call: Call<MusicResult<String>>, response: Response<MusicResult<String>>) {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val json = errorBody.string()
                            val body = Gson().fromJson<ErroBody>(json, ErroBody::class.java)
                            if (body.code == 2333 || body.result == "ERROR") {
                                val msg = validateHandler.obtainMessage()
                                msg.arg1 = Args_Failure
                                bundle.putString(Const.Key.KEY_MSG, "抱歉！\n因为版权或付费原因，此歌曲暂时无法播放，\n请您谅解！")
                                msg.data = bundle
                                validateHandler.sendMessage(msg)
                            } else {
                                player.setDataSource(music.url)
                                player.prepare()
                                player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                            }
                        }
                    }

                })

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

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
                    val totalDur = player.duration.toLong()
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
        ivPrevious.setOnClickListener{

        }
        ivNext.setOnClickListener {

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
        player.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                Log.d("-----MPA", "onError: what$what extra:$extra")
                return false
            }
        })
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
        }
        ivPrevious.setOnClickListener {
            if (curPosition == -1) {
                ToastUtils.showShort("未知错误")
                return@setOnClickListener
            }
            if (curPosition == 0) {
                ToastUtils.showShort("当前正在播放第一首歌")
                return@setOnClickListener
            }
            music = musicList[curPosition-1]
            curPosition -= 1
            switchMusic(music)
        }
        ivNext.setOnClickListener {
            if (curPosition == -1) {
                ToastUtils.showShort("未知错误")
                return@setOnClickListener
            }
            if (curPosition == musicList.size - 1) {
                ToastUtils.showShort("当前正在播放最后一首歌")
                return@setOnClickListener
            }
            music = musicList[curPosition+1]
            curPosition += 1
            switchMusic(music)
        }
    }

    private fun showLrc() {
        ivMusicPic.visibility = View.GONE
    }

    private fun hideLrc() {
        ivMusicPic.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun switchMusic(music: Music) {
        player.stop()
        player.reset()
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

    private fun releseMediaPlayer() {
        if (player != null) {
            player.stop()
            hadDestroy = true
            player.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releseMediaPlayer()
    }

    private class ProgressHandler(activity : MusicPlayActivity) : Handler() {

        private val weakReference : WeakReference<MusicPlayActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val activity = weakReference.get()
            if (msg != null && activity != null) {
                val data = msg.data
                val curTime = data.getString("curTime")
                activity.seekBar.progress = msg.what
                activity.tvCurTime.text = curTime
            }
        }
    }

    inner class SeekBarThread : Runnable {
        override fun run() {
            if (player != null && !hadDestroy) {
                val curTime = TimeUtils.date2String(Date(player.currentPosition.toLong()), Const.DateFormat.MMSS)
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
