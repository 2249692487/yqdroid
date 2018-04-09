package com.ywqln.yqdroid.app;


import android.content.Context;
import android.text.TextUtils;

import com.ywqln.yqdroid.BuildConfig;
import com.ywqln.yqdroid.app.interceptor.HeaderManagerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述:Api创建
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/11/28
 */
public final class ApiCreator {

    private static volatile ApiCreator sApiCreator;
    private Retrofit.Builder mBuilder;
    private HostManagerCallBack hostManagerCallBack;

    private ApiCreator(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HeaderManagerInterceptor(context));

        if (!BuildConfig.RELEASE_FLAVOR.equals(BuildConfig.FLAVOR)) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        mBuilder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());
    }

    /**
     * 初始化
     */
    static void init(Context context) {
        if (sApiCreator == null) {
            synchronized (ApiCreator.class) {
                if (sApiCreator == null) {
                    sApiCreator = new ApiCreator(context);
                    sApiCreator.hostManagerCallBack = new AppHostManager();
                }
            }
        }
    }

    /**
     * 创建
     */
    public static <T> T create(Class<T> clazz) {
        String host = sApiCreator.hostManagerCallBack.intercept(clazz);
        if (!TextUtils.isEmpty(host)) {
            return sApiCreator.mBuilder.baseUrl(host).build().create(clazz);
        }
        return sApiCreator.mBuilder.build().create(clazz);
    }

    /**
     * 请求头管理对象的接口描述
     */
    interface HostManagerCallBack {
        String intercept(Class clazz);
    }
}
