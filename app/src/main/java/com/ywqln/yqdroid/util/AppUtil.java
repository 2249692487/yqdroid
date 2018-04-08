package com.ywqln.yqdroid.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by yanwenqiang on 2017/12/3.
 * <p>
 * 描述:app工具类
 * @author zhangfan3
 */
public class AppUtil {

    /**
     * 获取设备DevicesId
     *
     * @param context context
     * @return DevicesId
     */
    public String deviceId;

    public static String getDevicesId(Context context) {
        String deviceId;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "请设置手机读写权限", Toast.LENGTH_LONG).show();
            return com.ywqln.yqdroid.util.StringUtil.Empty;
        }
        return tm.getDeviceId();
    }
}
