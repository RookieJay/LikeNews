package pers.ll.likenews.view.fragment

import kotlinx.android.synthetic.main.fragment_like_news.*
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const
import pers.ll.likenews.utils.ToastUtils

class CenterFragment : BaseFragment() {

    private var fragType = 0

    override fun setContentView(): Int {
        return R.layout.fragment_center
    }

    override fun initView() {
        val bundle = arguments
        if (bundle != null) {
            fragType = bundle.getInt(Const.Key.START_TYPE, 0)
            val fragmentManager = childFragmentManager
            val trans = fragmentManager.beginTransaction()
            when(fragType) {
                0 -> trans.add(R.id.flContainer, LikeNewsFragment())
                1 -> trans.add(R.id.flContainer, LikeNewsFragment())
                2 -> trans.add(R.id.flContainer, LikeMusicFragment())
                3 -> trans.add(R.id.flContainer, LikeMovieFragment())
            }
            trans.commitAllowingStateLoss()
        }
    }

    override fun loadData() {
    }
}
