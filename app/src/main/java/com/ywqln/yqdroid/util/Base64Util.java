package com.ywqln.yqdroid.util;

import android.util.Base64;

/**
 * Created by yanwenqiang on 2017/12/7.
 * <p>
 * 描述:Base64 工具类
 *
 * @author zhangfan3
 */
public class Base64Util {

    /**
     * byte转base64String
     *
     * @param bytes
     * @return
     */
    public static String byteChangeBase64String(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
