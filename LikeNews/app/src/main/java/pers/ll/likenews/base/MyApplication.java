package pers.ll.likenews.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Environment;

import com.jinrishici.sdk.android.JinrishiciClient;
import com.jinrishici.sdk.android.factory.JinrishiciFactory;
import pers.ll.likenews.utils.AppUtils;
import pers.ll.likenews.utils.CrashUtils;
import pers.ll.likenews.utils.LogUtils;

/**
 * 自定义Application，初始化一些全局数据
 * Application类全局只有一个，它本身就是一个单例,不需要new一个出来
 */
public class MyApplication extends Application {

    private static MyApplication app;
    private Typeface typeface;

    public static MyApplication getInstance() {
        return app;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AppUtils.init(this);
        String logPath = String.format("%s/%s/log", Environment.getExternalStorageDirectory().toString(),
                getPackageName());
        CrashUtils.init(logPath);
        LogUtils.getConfig().setDir(logPath).setFilePrefix("LikeNews");
        JinrishiciFactory.init(this);
        typeface = Typeface.createFromAsset(app.getAssets(), "fonts/FZZJ-ZHZHXKJW.TTF");//下载的字体
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }


}
