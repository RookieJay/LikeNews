package pers.ll.likenews.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import pers.ll.likenews.model.AppModel;

public class AppManager {

    private static AppManager mInstance = null;

    private Context mContext;
    private PackageManager pm;
    private List<PackageInfo> mListAllApp;

    private AppManager(Context mContext) {
        this.mContext = mContext;

        initAppManager();
    }

    private void initAppManager() {
        pm = mContext.getPackageManager();
        mListAllApp = pm.getInstalledPackages(0);
    }

    public static AppManager getInstance(Context mContext) {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager(mContext);
                }
            }
        }
        return mInstance;
    }

    //获取系统安装的所有应用
    public List<AppModel> getAllApp() {
        List<AppModel> mList = new ArrayList<>();
        //3.遍历
        for (PackageInfo info : mListAllApp) {
            ApplicationInfo applicationInfo = info.applicationInfo;
            AppModel model = new AppModel();
            model.setIcon(applicationInfo.loadIcon(pm));
            model.setAppName(pm.getApplicationLabel(applicationInfo).toString());
            model.setPackageName(applicationInfo.packageName);
            //应用大小
            File file = new File(applicationInfo.sourceDir);
            model.setAppSize(formatData(file));
            //判断是否系统应用
            int flag = applicationInfo.flags;
            if ((flag & ApplicationInfo.FLAG_SYSTEM) != 0) {
                model.setSystem(true);
            }
            mList.add(model);
        }
        return mList;
    }

    /**
     * 计算传入的File大小
     *
     * @param file
     * @return
     */
    private String formatData(File file) {
        float fileSize = file.length() / 1024 / 1024;
        DecimalFormat df = new DecimalFormat("0.00");
        if (fileSize > 1024) {
            float sizes = fileSize / 1024;
            return df.format(sizes) + "G";
        } else {
            return df.format(fileSize) + "MB";
        }
    }

    public String getAppName(String packageName) {
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(packageName, 0);
            ApplicationInfo applicationInfo = info.applicationInfo;
            return pm.getApplicationLabel(applicationInfo).toString();
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获取指定包名的版本号
     *
     * @param packageName
     * @return
     */
    public String getAppVersion(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            return "版本号:" + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "版本号:获取失败";
        }
    }

    /**
     * 获取指定包名的icon
     *
     * @param packageName
     * @return
     */
    public Drawable getAppIcon(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            Drawable drawable = info.applicationInfo.loadIcon(pm);
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定包名的权限
     *
     * @param packageName
     * @return
     */
    public String[] getAppPermissions(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            String[] permissions = info.requestedPermissions;
            return permissions;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e.toString());
            return null;
        }
    }

    /**
     * 获取指定包名应用大小
     * @param packageName
     * @return
     */
    public String getAppSize(String packageName) {
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            ApplicationInfo applicationInfo = info.applicationInfo;
            File file = new File(applicationInfo.sourceDir);
            return formatData(file);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0B";
        }

    }

    /**
     * 卸载指定包名的应用
     * @param packageName
     */
    public void unInstallApp(String packageName){
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri uri = Uri.parse("package:" + packageName);
        intent.setData(uri);
        mContext.startActivity(intent);
    }
}
