package com.ywqln.yqdroid.util;

import android.text.TextUtils;

/**
 * 描述:文本处理
 * <p>
 *
 * @author yanwenqiang
 * @date 2015/6/26.
 */
public class StringUtil {

     /**
     * 空字符串,为避免不必要的创建空字符串对象.
     */
    public static final String Empty = "";

    public static final String Zero = "0";

    public static String nullToEmpty(String value) {
        if (value == null) {
            value = Empty;
        }
        return value;
    }

    public static boolean isNullOrEmpty(String value) {
        if (value == null) {
            return true;
        }
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        return false;
    }
}
