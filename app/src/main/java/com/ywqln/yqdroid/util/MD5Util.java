package com.ywqln.yqdroid.util;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述:MD5加密
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public class MD5Util {

    public static String getHeaderMD5(final String message) {
        StringBuilder result = new StringBuilder();
        result.append(md5(message));
        final int lenght = result.length();
        if (lenght > 3) {
            return md5(result.delete(lenght - 2, lenght).toString());
        } else {
            return "";
        }
    }

    public static String md5(final String message) {
        StringBuilder result = new StringBuilder();
        if (TextUtils.isEmpty(message)) {
            return result.toString();
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] input = message.getBytes();
            byte[] buff = messageDigest.digest(input);

            for (int digital : buff) {
                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    result.append("0");
                }
                result.append(Integer.toHexString(digital));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
