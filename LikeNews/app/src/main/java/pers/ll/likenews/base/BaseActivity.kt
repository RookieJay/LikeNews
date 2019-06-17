package pers.ll.likenews.base

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pers.ll.likenews.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //禁止横屏
    }
}
