package pers.ll.likenews.view.fragment

import android.content.Context
import android.view.MotionEvent
import kotlinx.android.synthetic.main.fragment_songsheet_list.*
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.utils.UIUtils

class SongSheetListFragment : BaseFragment(), LikeMusicFragment.SongSheetTouchListener  {

    private lateinit var parentFrag : LikeMusicFragment

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parentFrag = context as LikeMusicFragment
    }

    override fun setContentView(): Int {
        return R.layout.fragment_songsheet_list
    }

    override fun initView() {
        setListener()

    }

    override fun loadData() {

    }

    private fun setListener() {
        //注册触摸事件
        parentFrag.registerMyTouchListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //不在litePager范围类
        return !UIUtils.inRangeOfView(litePager, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFrag.unRegisterMyTouchListener(this)
    }
}
