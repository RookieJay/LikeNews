package pers.ll.likenews.utils;

import android.app.Application;

public final class AppUtils {

    private static Application mApplication;

    public static void init(Application application) {
        mApplication = application;
    }

    public static Application getApp() {
        if (null != mApplication) {
            return mApplication;
        }
        throw new NullPointerException("请初始化Application");
    }
}
