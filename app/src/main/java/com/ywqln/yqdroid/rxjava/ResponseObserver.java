package com.ywqln.yqdroid.rxjava;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * 描述:Api Observer
 * <p>
 * Created by yanwenqiang on 2017/8/31
 */

public abstract class ResponseObserver<T> implements Observer<T> {

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof ApiException) {
            error((ApiException) e);
        } else {
            error(new ApiException(ApiException.NET_ERROR, e.getMessage()));
        }
    }

    /**
     * error
     */
    public abstract void error(ApiException e);
}
