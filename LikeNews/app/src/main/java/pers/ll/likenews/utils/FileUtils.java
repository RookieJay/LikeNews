package pers.ll.likenews.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by LX5 on 2019/5/11.
 */

public class FileUtils {
    //内存大小 0:总大小 1:剩余大小  默认是B
    public static long getSDSize(int index) {
        File file = Environment.getExternalStorageDirectory();
        //计算一个文件的大小
        StatFs statFs = new StatFs(file.getPath());
        //每一块内存大小
        long tota = statFs.getBlockSize();
        if (index == 0) {
            //总共存储的数量
            long sizeAll = statFs.getBlockCount();
            return tota * sizeAll;
        } else if (index == 1) {
            //还未使用的区块大小
            long avail = statFs.getAvailableBlocks();
            return tota * avail;
        }
        return 0;
    }

    //已用运存大小 1:总 0：已用
    public static long getAvailMemory(Context mContext, int index){
        //手机的运存信息 /proc/meminfo 前两行
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        if(index == 0){
            return mi.availMem;
        }else if(index == 1){
            return mi.totalMem;
        }
        return 0;
    }

    //获取内核版本
    public static String getLinuxVersion() {
        Process process = null;
        String kernelVersion = "";
        try {
            process = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the output line
        InputStream outs = process.getInputStream();
        InputStreamReader isrout = new InputStreamReader(outs);
        BufferedReader brout = new BufferedReader(isrout, 8 * 1024);


        String result = "";
        String line;
        // get the whole standard output string
        try {
            while ((line = brout.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            if (result != "") {
                String Keyword = "version ";
                int index = result.indexOf(Keyword);
                line = result.substring(index + Keyword.length());
                index = line.indexOf(" ");
                kernelVersion = line.substring(0, index);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return kernelVersion;
    }
}
