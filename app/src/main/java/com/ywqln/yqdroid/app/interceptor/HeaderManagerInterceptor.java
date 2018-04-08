package com.ywqln.yqdroid.app.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ywqln.yqdroid.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 管理请求报文头
 *
 * @author yanwenqiang
 * @date 2017/12/03
 */

public class HeaderManagerInterceptor implements Interceptor {

    private Context mContext;

    public HeaderManagerInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        String requestUrl = request.url().toString();
        boolean isHkUrl = requestUrl.startsWith(BuildConfig.HK_HOST);
        // 通过HostUrl来确定应该灌入哪种HttpHeader
        if (isHkUrl) {
            return new HkApiHeaderInterceptor(mContext).intercept(chain);
        }

        return chain.proceed(request);
    }
}
