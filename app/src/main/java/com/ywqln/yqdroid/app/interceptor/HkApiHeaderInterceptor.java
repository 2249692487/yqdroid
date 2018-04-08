package com.ywqln.yqdroid.app.interceptor;

import android.content.Context;

import com.ywqln.yqdroid.BuildConfig;
import com.ywqln.yqdroid.constant.SprfConstant;
import com.ywqln.yqdroid.util.SprfUtil;
import com.ywqln.yqdroid.util.StringUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yanwenqiang on 2017/12/2.
 * <p>
 * 描述:HouseKeeper接口的请求头拦截器
 */
public class HkApiHeaderInterceptor implements Interceptor {

    private Context mContext;

    public HkApiHeaderInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder
                // platform:    android
                .addHeader("platform", "android")
                // channel:     渠道
                .addHeader("channel", "faceCollect")
                // CompanyCode: 城市code
                .addHeader("CompanyCode", SprfUtil.getString(mContext,SprfConstant.CITY_CODE,StringUtil.Empty))
                // Udid:        设备串号
                .addHeader("Udid", SprfUtil.getString(mContext, SprfConstant.UDID, StringUtil.Empty))
                // ClientVer:   当前版本号
                .addHeader("ClientVer", BuildConfig.VERSION_NAME)
                // HKSession:   登录接口返回
                .addHeader("HKSession", SprfUtil.getString(mContext, SprfConstant.HK_SESSION, StringUtil.Empty));
        return chain.proceed(requestBuilder.build());
    }
}
