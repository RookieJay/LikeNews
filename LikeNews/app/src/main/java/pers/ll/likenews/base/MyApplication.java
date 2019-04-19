package pers.ll.likenews.base;

import android.app.Application;
import pers.ll.likenews.utils.AppUtils;

/**
 * 自定义Application，初始化一些全局数据
 * Application类全局只有一个，它本身就是一个单例,不需要new一个出来
 */
public class MyApplication extends Application {

    private static Application app;

    public static Application getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppUtils.init(this);
    }


}
