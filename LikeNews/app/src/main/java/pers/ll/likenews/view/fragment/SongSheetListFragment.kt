package pers.ll.likenews.view.fragment

import android.view.MotionEvent
import com.wuyr.litepager.LitePager
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.utils.UIUtils

class SongSheetListFragment : BaseFragment(), LikeMusicFragment.SongSheetTouchListener  {

    private lateinit var parentFrag : LikeMusicFragment
    private lateinit var litePager: LitePager


    override fun setContentView(): Int {
        return R.layout.fragment_songsheet_list
    }

    override fun initView() {
        parentFrag = parentFragment as LikeMusicFragment
        litePager = findViewById(R.id.litePager) as LitePager
        setListener()
    }

    override fun loadData() {

    }

    private fun setListener() {
        //注册触摸事件
        parentFrag.registerMyTouchListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //在litePager范围类
        return UIUtils.inRangeOfView(litePager, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentFrag.unRegisterMyTouchListener(this)
    }
}
