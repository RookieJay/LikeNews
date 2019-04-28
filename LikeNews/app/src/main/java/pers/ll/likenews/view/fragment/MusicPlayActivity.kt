package pers.ll.likenews.view.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.Animation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_music_play.*
import kotlinx.android.synthetic.main.include_base_toolbar.*
import pers.ll.likenews.R
import pers.ll.likenews.consts.Const
import pers.ll.likenews.model.Music
import pers.ll.likenews.utils.ImageUtil
import pers.ll.likenews.utils.MainHandler
import android.view.animation.LinearInterpolator




class MusicPlayActivity : AppCompatActivity() {

    private lateinit var music : Music
    private lateinit var imageUtil : ImageUtil
    private var isPlaying = true
    private lateinit var objectAnimator : ObjectAnimator
    private lateinit var ivPauseDrawable : Drawable

    private val STATE_PLAYING = 1 //正在播放
    private val STATE_PAUSE = 2 //暂停
    private val STATE_STOP = 3 //停止
    private var state : Int = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_play)
        music = intent.getParcelableExtra(Const.Key.KEY_MUSIC)
        initView()
        setListener()
    }

    @SuppressLint("ObjectAnimatorBinding")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {
        barLeft.visibility = View.VISIBLE
        barTitle.visibility = View.VISIBLE
        barTitle.text = music.name
        ivPauseDrawable = ivPause.drawable
        ivPauseDrawable.setTint(ContextCompat.getColor(ivPause.context, R.color.colorAccent))
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
            playMusic()
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun playMusic(){
        if(state == STATE_STOP){
            objectAnimator.start() //动画开始
            state = STATE_PLAYING
        }else if(state == STATE_PAUSE){
            objectAnimator.resume() //动画重新开始
            state = STATE_PLAYING
        }else if(state == STATE_PLAYING){
            objectAnimator.pause() //动画暂停
            state = STATE_PAUSE
        }
    }

    private fun stopMusic() {
        objectAnimator.end()//动画结束
        state = STATE_STOP
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
                    objectAnimator.pause() //动画暂停
                    state = STATE_PAUSE
                    ivPauseDrawable.setTint(ContextCompat.getColor(ivPause.context, R.color.colorAccent))
                }

            } else{
                ivPause.setImageResource(R.drawable.ic_pause_circle)
                if (objectAnimator != null) {
                    objectAnimator.resume()
                    state = STATE_PLAYING
                    ivPauseDrawable.setTint(ContextCompat.getColor(ivPause.context, R.color.colorAccent))
                }
            }
            isPlaying = !isPlaying

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
