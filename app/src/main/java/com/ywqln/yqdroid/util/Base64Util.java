package com.ywqln.yqdroid.util;

import android.util.Base64;

/**
 * 描述:Base64 工具类
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public class Base64Util {

    /**
     * byte转base64String
     */
    public static String byteChangeBase64String(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
