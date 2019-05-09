package pers.ll.likenews.view.fragment

import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment
import pers.ll.likenews.consts.Const

class CenterFragment : BaseFragment() {

    private var fragType = 0

    override fun setContentView(): Int {
        return R.layout.fragment_center
    }

    override fun initView() {
        val bundle = arguments
        if (bundle != null) {
            fragType = bundle.getInt(Const.Key.START_TYPE, 0)
            replace()
        }
    }

    override fun loadData() {
    }

    private fun replace() {
        val fragmentManager = childFragmentManager
        val trans = fragmentManager.beginTransaction()
        when(fragType) {
            0 -> trans.replace(R.id.flContainer, LikeNewsFragment())
            1 -> trans.replace(R.id.flContainer, LikeNewsFragment())
            2 -> trans.replace(R.id.flContainer, LikeMusicFragment())
            3 -> trans.replace(R.id.flContainer, LikeMovieFragment())
        }
        trans.commitAllowingStateLoss()
    }

    fun changePage(type: Int) {
        fragType = type
        replace()
    }

}
