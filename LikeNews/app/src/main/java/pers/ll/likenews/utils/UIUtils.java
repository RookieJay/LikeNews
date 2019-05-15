package pers.ll.likenews.utils;

import android.app.AlertDialog;
import android.content.Context;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import pers.ll.likenews.R;

public class UIUtils {

    public static void showDialog(Context context, String title, String message) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle(title)//标题
                        .setMessage(message)//内容
                        .setIcon(R.drawable.icon_music_placeholder)//图标
                        .create();
                alertDialog.show();
    }

    public static int getColor(Context context, int resId){
        return ContextCompat.getColor(context, resId);
    }

    //判断当前View 是否处于touch中
    public static boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y
                + view.getHeight())) {
            return false;
        }
        return true;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置状态栏和导航栏透明
     * @param window
     */
    public static void setTrans(Window window) {
        window.requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 隐藏状态栏和导航栏
     */
    public static void hideStatusAndNavBar(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {  //当系统版本大于5.0时执行
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE //两个FLAG一起用表示会让应用的主体内容占用系统状态栏的空间
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;    //让应用的主体内容占用系统导航栏的空间
            decorView.setSystemUiVisibility(option);
            window.setNavigationBarColor(Color.TRANSPARENT);   //设置导航栏透明
            window.setStatusBarColor(Color.TRANSPARENT);   //设置状态栏透明
        }
    }

    public static void setSameColorBar(Boolean isLight, Window window, Resources resources) {
        if (Build.VERSION.SDK_INT >= 21) {
            //LAYOUT_FULLSCREEN 、LAYOUT_STABLE：让应用的主体内容占用系统状态栏的空间；
            //            View decorView = getWindow().getDecorView();
            //            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            //            decorView.setSystemUiVisibility(option);
            //            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            if (isLight) {
                window.setStatusBarColor(resources.getColor(R.color.colorPrimary));
            } else {
                window.setStatusBarColor(resources.getColor(R.color.colorAccent));
            }
            //状态栏颜色接近于白色，文字图标变成黑色
            View decor = window.getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (isLight) {
                //light --> a|=b的意思就是把a和b按位或然后赋值给a,   按位或的意思就是先把a和b都换成2进制，然后用或操作，相当于a=a|b
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                //dark  --> &是位运算里面，与运算,  a&=b相当于 a = a&b,  ~非运算符
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);
        }
    }

}
