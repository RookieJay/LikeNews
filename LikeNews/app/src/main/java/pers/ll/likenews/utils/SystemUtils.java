package pers.ll.likenews.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.load.engine.Resource;

public class SystemUtils {

    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 获取状态栏高度——方法1
     * */
    public static int getStatusBarHeight(Resources resource) {

        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = resource.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = resource.getDimensionPixelSize(resourceId);
        }
        Log.v("-------", "status bar height:"+ statusBarHeight);
        return statusBarHeight;
    }

    public static int getNavigationBarHeight(Resources resources) {

        int resourceId = resources.getIdentifier("navigation_bar_height","dimen","android");

        int height = resources.getDimensionPixelSize(resourceId);

        Log.v("navigation bar>>>", "height:" + height);

        return height;

    }
}
