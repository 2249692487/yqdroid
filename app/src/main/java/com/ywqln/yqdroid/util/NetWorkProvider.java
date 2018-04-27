package com.ywqln.yqdroid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络支持类
 * Created by yanwenqiang on 2015/6/30.
 */
public final class NetWorkProvider {
    /**
     * 网络是否可用
     */
    public static boolean netWorkEnable;

    /**
     * 网络类型 none;mobile;wifi
     */
    public static String netWorkType;

    /**
     * wifi开关是否开启
     */
    public static boolean wifiSwicth;

    public static void checkNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            netWorkEnable = networkInfo.isAvailable();
            netWorkType = networkInfo.getTypeName().equalsIgnoreCase("WIFI") ? "wifi" : "mobile";
        } else {
            netWorkEnable = false;
            netWorkType = "none";
        }
    }

    public static void setWifiSwicth(boolean swicth) {
        wifiSwicth = swicth;
    }

}
