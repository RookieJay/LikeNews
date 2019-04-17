package pers.ll.likenews.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.Exception

abstract class BaseFragment : Fragment() {

    private var TAG = this.javaClass.simpleName
    private var mHaveLoadData = false   //  是否请求过数据
    private var isInitView = false
    protected var mRootView : View? = null
    private var isVisibleToUser = false


    /**
     * 初始化布局
     */
    abstract fun initLayoutId() : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = initLayoutId()
        if (layoutId == 0) {
            throw Exception("初始化界面失败，如果您不希望使用layoutId，请重写onCreateView()方法实现您自己的view")
        }
        mRootView = inflater.inflate(layoutId, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isInitView = true
        lazyLoad()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "启动")
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
            lazyLoad()
            mHaveLoadData = true
        } else{
            this.isVisibleToUser = false
        }
    }

    /**
     * 检查是否可以开始加载数据
     */
    private fun lazyLoad() {
        Log.d(TAG, "懒加载")
        // 双重标记：如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (isVisibleToUser && isInitView) {
            loadData()
            //防止重复加载
            isVisibleToUser = false
            isInitView = false
        }
    }

    /**
     * 开始加载数据
     */
    abstract fun loadData()

    protected fun findViewById(id : Int) : View? {
        return if (view != null && id > 0) {
            view!!.findViewById(id)
        } else {
            null
        }
    }
}