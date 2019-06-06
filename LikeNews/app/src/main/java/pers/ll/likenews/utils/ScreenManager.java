package pers.ll.likenews.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by LX5 on 2019/5/11.
 */

public class ScreenManager {
    private Context mContext;
    private static ScreenManager mInstance = null;
    private  WindowManager wm;
    private DisplayMetrics dm;

    private ScreenManager( Context mContext){
        this.mContext = mContext;
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
    }

    public static ScreenManager getInstance(Context mContext){
        if(mInstance == null){
            synchronized (ScreenManager.class){
                if(mInstance == null){
                    mInstance = new ScreenManager(mContext);
                }
            }
        }
        return mInstance;
    }

    public int getW(){
        return dm.widthPixels;
    }

    public int getH(){
        return dm.heightPixels;
    }
}
