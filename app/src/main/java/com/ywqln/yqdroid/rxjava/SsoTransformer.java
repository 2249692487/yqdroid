package com.ywqln.yqdroid.rxjava;

import com.ywqln.yqdroid.entity.SsoApiRespDo;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yanwenqiang on 2017/10/26.
 * <p>
 * 描述:HouseKeeper的请求数据转换
 * @author yanwenqiang
 */
public class SsoTransformer<T> implements ObservableTransformer<SsoApiRespDo<T>, T> {

    private final LifecycleTransformer<T> mLifecycleTransformer;
    private int mSucCode;

    public SsoTransformer(LifecycleTransformer<T> mLifecycleTransformer) {
        this.mLifecycleTransformer = mLifecycleTransformer;
    }


    @Override
    public ObservableSource<T> apply(@NonNull io.reactivex.Observable<SsoApiRespDo<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(thkApiRespDo -> {
                    mSucCode = 0;
                    if (thkApiRespDo.getResultNo()  != mSucCode) {
                        throw new ApiException(thkApiRespDo.getResultNo(), thkApiRespDo.getMessage().toString());
                    }
                    return thkApiRespDo.getResult();
                })
                .compose(mLifecycleTransformer);
    }
}
