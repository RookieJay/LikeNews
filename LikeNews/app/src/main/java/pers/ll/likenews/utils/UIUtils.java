package pers.ll.likenews.utils;

import android.app.AlertDialog;
import android.content.Context;

import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
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
}
