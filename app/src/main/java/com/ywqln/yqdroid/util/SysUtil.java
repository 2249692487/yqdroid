package com.ywqln.yqdroid.util;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * 描述:系统方法工具
 * <p>
 *
 * @author yanwenqiang
 * @date 2015/6/26.
 */
public final class SysUtil {

    private SysUtil() {
        //Utility Class
    }

    /**
     * 获取设备DevicesId
     *
     * @param context context
     * @return DevicesId
     */
    public static String getDevicesId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, "请设置手机手写权限", Toast.LENGTH_LONG);
            return "";
        }
        return tm.getDeviceId();
    }

    /**
     * 当前进程名称
     */
    public static String currentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : activityManager
                    .getRunningAppProcesses()) {
                if (appProcessInfo.pid == pid) {
                    return appProcessInfo.processName;
                }
            }
        }
        return null;
    }
}
