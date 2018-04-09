package com.ywqln.yqdroid.rxjava;

/**
 * Created by yanwenqiang on 17/6/19.
 * <p>
 * 描述:Common API Exception
 *
 * @author yanwenqiang
 */
public final class ApiException extends RuntimeException {

    /**
     * 网络错误
     */
    public static final int NET_ERROR = 1000;

    public final int code;
    public final String message;

    public ApiException(int code) {
        this(code, null);
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
