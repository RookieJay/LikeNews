package pers.ll.likenews.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

/**
 * Created by LX5 on 2019/5/11.
 */

public class MacUtils {
    public static String getMac(Context mContext) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        String mac = wifiManager.getConnectionInfo().getMacAddress();
        if(!TextUtils.isEmpty(mac)){
            return mac;
        }
        return  "";
    }
}
