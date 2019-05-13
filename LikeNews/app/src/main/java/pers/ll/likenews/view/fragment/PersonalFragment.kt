package pers.ll.likenews.view.fragment

import android.widget.TextView
import com.jinrishici.sdk.android.JinrishiciClient
import com.jinrishici.sdk.android.api.JinrishiciAPI
import pers.ll.likenews.R
import pers.ll.likenews.base.BaseFragment

class PersonalFragment : BaseFragment() {

    private lateinit var tvTest : TextView

    override fun setContentView(): Int {
        return R.layout.fragment_personal
    }

    override fun initView() {
    }

    override fun loadData() {
        val client = JinrishiciClient()
    }
}
