package pers.ll.likenews.utils;

import android.app.AlertDialog;
import android.content.Context;

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
}
