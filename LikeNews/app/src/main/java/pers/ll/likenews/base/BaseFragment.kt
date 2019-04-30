package pers.ll.likenews.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pers.ll.likenews.utils.ToastUtils
import java.lang.Exception

abstract class BaseFragment : Fragment() {

    private var TAG = this.javaClass.simpleName
    private var mHaveLoadData = false   //  是否请求过数据
    private var isInitView = false
    protected var mRootView : View? = null
    private var isVisibleToUser = false
    protected lateinit var mContext : Context
    private lateinit var mActivity : Activity

    override fun getContext() : Context {
        return MyApplication.getInstance()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = this.context
        mActivity = context as Activity
    }

    /**
     * 初始化布局
     */
    protected abstract fun setContentView() : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        val layoutId = setContentView()
        if (layoutId == 0) {
            throw Exception("初始化界面失败，如果您不希望使用layoutId，请重写onCreateView()方法实现您自己的view")
        }
        mRootView = inflater.inflate(layoutId, container, false)
        return mRootView
    }

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        isInitView = true
        canLoadData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")

    }

    /**
     * 在所有生命周期函数之前调用，查看是否可见
     * setUserVisibleHint()会多次回调
     * setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
     * 如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
     * 如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
     * 总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
     * 如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        print("setUserVisibleHint$isVisibleToUser")
        if (isVisibleToUser) {
            this.isVisibleToUser = true
            canLoadData()
            mHaveLoadData = true
        } else{
            this.isVisibleToUser = false
        }
    }

    /**
     * 检查是否可以开始加载数据
     */
    private fun canLoadData() {
        // 双重标记：如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (isVisibleToUser && isInitView) {
//            ToastUtils.showShort("${TAG}开始加载数据")
            loadData()
            //防止重复加载
            isVisibleToUser = false
            isInitView = false
        }
    }

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    protected fun findViewById(id : Int) : View? {
        return if (view != null && id > 0) {
            view!!.findViewById(id)
        } else {
            null
        }
    }

    protected fun runOnUIThread(runnable: Runnable) {
        mActivity.runOnUiThread(runnable)
    }
}