package pers.ll.likenews.view.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import android.widget.TextView
import pers.ll.likenews.utils.*
import java.lang.ref.WeakReference
import java.util.*


class MusicPlayActivity : AppCompatActivity() {

    private lateinit var music : Music
    private lateinit var imageUtil : ImageUtil

    private lateinit var objectAnimator : ObjectAnimator
    private val STATE_PLAYING = 1 //正在播放
    private val STATE_PAUSE = 2 //暂停
    private val STATE_STOP = 3 //停止
    private var state : Int = 0
    private var hadDestroy = false
    private lateinit var player : MediaPlayer
    private var isPlaying = true
    private var isRepeat = false
    private lateinit var tvMusicDuration : TextView
    private lateinit var tvCurTime : TextView
    private lateinit var seekBar : SeekBar
    private val handler = ProgressHandler(this)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_play)
        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        initView()
        initPlayer()
        playMusic()
        setListener()
    }

    @SuppressLint("ObjectAnimatorBinding")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        //跑马灯必须加
        barTitle.isSelected = true
        barTitle.isFocusable = true
        barTitle.isFocusableInTouchMode = true
        barTitle.text = music.name
        if (music.pic != null) {
            imageUtil = ImageUtil.getInstance()
            Glide.with(ivMusicPic.context)
                .load(music.pic)
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(ivMusicPic.context, R.drawable.icon_music_placeholder))
                .error(ContextCompat.getDrawable(ivMusicPic.context, R.drawable.icon_music_placeholder))
                .into(ivMusicPic)
            Thread(Runnable {
                val bitmap = imageUtil.url2BitMap(music.pic)
                if (bitmap != null) {
                    val overLay = imageUtil.blur(bitmap, rlMusicPlayer)
                    MainHandler.getInstance().post {
                        rlMusicPlayer.background = imageUtil.getDrawbleFormBitmap(rlMusicPlayer.context, overLay) }
                }
            }).start()
            //（1）LinearInterpolator：动画从开始到结束，变化率是线性变化。
            //（2）AccelerateInterpolator：动画从开始到结束，变化率是一个加速的过程。
            //（3）DecelerateInterpolator：动画从开始到结束，变化率是一个减速的过程。
            //（4）CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
            //（5）AccelerateDecelerateInterpolator：动画从开始到结束，变化率是先加速后减速的过程。
//            operatingAnim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
//            val lin = LinearInterpolator()
//            operatingAnim.interpolator = lin

            state = STATE_STOP
            objectAnimator = ObjectAnimator.ofFloat(ivMusicPic, "rotation", 0f, 360f) //添加旋转动画，旋转中心默认为控件中点
            objectAnimator.duration = 20000 //设置动画时间
            objectAnimator.interpolator = LinearInterpolator() //动画时间线性渐变
            objectAnimator.repeatCount = ObjectAnimator.INFINITE
            objectAnimator.repeatMode = ObjectAnimator.RESTART
            tvMusicDuration = findViewById(R.id.tvMusicDuration)
            tvCurTime = findViewById(R.id.tvCurTime)
            seekBar = findViewById(R.id.seekBar)
        }
    }

    private fun initPlayer(){
        Log.d("-----MPA", "initPlayer")
        player = MediaPlayer()
        try {
            player.setDataSource(music.url)
            player.prepare()
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } catch (e: Exception) {
            e.printStackTrace()
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
            } else {
                ivRecycle.setImageResource(R.drawable.ic_repeat_one)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        if (player != null) {
            player.stop()
            hadDestroy = true
            player.release()
        }
    }

    inner class ProgressHandler(activity : MusicPlayActivity) : Handler() {

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
//                Thread.sleep(80)
                handler.postDelayed(this, 80)
            } catch (e: Exception) {
            }
        }
    }

}
