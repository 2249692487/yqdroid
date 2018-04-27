package com.ywqln.yqdroid.util;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Glide图片加载支持
 * Created by yanwenqiang on 2015/7/1.
 */
public final class GlideProvider {
    /**
     * 初始化
     */
    public static DrawableRequestBuilder<String> init(Activity activity) {
        return Glide.with(activity)
                .fromString()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade();
    }

    /**
     * 初始化
     */
    public static DrawableRequestBuilder<String> init(Fragment fragment) {
        return Glide.with(fragment)
                .fromString()
                .crossFade();
    }


    /**
     * 加载
     */
    public static void load(DrawableRequestBuilder<String> drawableRequestBuilder,
            ImageView imageView,
            String imgUrl, int placeRec, int errorRec) {
        drawableRequestBuilder.placeholder(placeRec)
                .error(errorRec)
                .load(imgUrl)
                .into(imageView);
    }

    /**
     * wifi开关控制下加载
     */
    public static void loadWithWifi(DrawableRequestBuilder<String> drawableRequestBuilder,
            ImageView imageView,
            String imgUrl, int placeRec, int errorRec) {
        if (NetWorkProvider.wifiSwicth) {
            if ("wifi".equals(NetWorkProvider.netWorkType)) {
                drawableRequestBuilder.placeholder(placeRec)
                        .error(errorRec)
                        .load(imgUrl)
                        .into(imageView);
            } else {
                drawableRequestBuilder.placeholder(placeRec)
                        .error(errorRec)
                        .load("")
                        .into(imageView);
            }
        } else {
            drawableRequestBuilder.placeholder(placeRec)
                    .error(errorRec)
                    .load(imgUrl)
                    .into(imageView);
        }
    }

    /**
     * wifi开关控制下加载
     */
    public static void loadWithWifi(DrawableRequestBuilder<Uri> drawableRequestBuilder,
            ImageView imageView,
            Uri uri, int placeRec, int errorRec) {
        if (NetWorkProvider.wifiSwicth) {
            if ("wifi".equals(NetWorkProvider.netWorkType)) {
                drawableRequestBuilder.placeholder(placeRec)
                        .error(errorRec)
                        .load(uri)
                        .into(imageView);
            } else {
                drawableRequestBuilder.placeholder(placeRec)
                        .error(errorRec)
                        .load(Uri.parse(""))
                        .into(imageView);
            }
        } else {
            drawableRequestBuilder.placeholder(placeRec)
                    .error(errorRec)
                    .load(uri)
                    .into(imageView);
        }
    }

}
