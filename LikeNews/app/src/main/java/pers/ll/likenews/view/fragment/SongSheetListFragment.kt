package pers.ll.likenews.view.fragment

import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import com.wuyr.litepager.LitePager
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.utils.ThreadPoolManager
import pers.ll.likenews.utils.UIUtils
import retrofit2.Retrofit

class SongSheetListFragment : BaseFragment(), LikeMusicFragment.SongSheetTouchListener  {

    private lateinit var parentFrag : LikeMusicFragment
    private lateinit var litePager: LitePager
    private lateinit var mRecyclerView : RecyclerView
    private var executor = ThreadPoolManager.getInstance()

    override fun setContentView(): Int {
        return R.layout.fragment_songsheet_list
    }

    override fun initView() {
        mRecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        parentFrag = parentFragment as LikeMusicFragment
        litePager = findViewById(R.id.litePager) as LitePager
        setListener()
    }

    override fun loadData() {
        executor.execute {
            Retrofit.Builder().baseUrl(Const.URL.BASE_URL_MOVIE)
        }
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
