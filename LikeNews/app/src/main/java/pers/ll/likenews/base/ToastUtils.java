package pers.ll.likenews.base;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast工具类
 */
public final class ToastUtils {

    private static Toast mToast;
    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    private ToastUtils() {
        throw new UnsupportedOperationException("Toast不能被初始化！");
    }

    /**
     *
     * @param msg   提示信息
     * @param duration  显示时长
     */
    private static void show(CharSequence msg, int duration) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                mToast = Toast.makeText(AppUtils.getApp(), msg, duration);
                mToast.show();
            }
        });

    }

    /**
     * 短时Toast 2s
     * @param msg
     */
    public static void showShort(CharSequence msg) {
        show(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 长时Toast 3.5s
     * @param msg
     */
    public static void showLong(CharSequence msg) {
        show(msg, Toast.LENGTH_LONG);
    }

    /**
     * 取消显示
     */
    private static void cancel() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

}
