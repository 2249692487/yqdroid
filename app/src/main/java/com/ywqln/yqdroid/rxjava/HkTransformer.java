package com.ywqln.yqdroid.rxjava;

import com.ywqln.yqdroid.entity.HkApiRespDo;
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
 *
 * @author yanwenqiang
 */
public class HkTransformer<T> implements ObservableTransformer<HkApiRespDo<T>, T> {

    private final LifecycleTransformer<T> mLifecycleTransformer;
    private int mErrorCode;

    public HkTransformer(LifecycleTransformer<T> mLifecycleTransformer) {
        this.mLifecycleTransformer = mLifecycleTransformer;
    }


    @Override
    public ObservableSource<T> apply(@NonNull io.reactivex.Observable<HkApiRespDo<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(thkApiRespDo -> {
                    mErrorCode = 200;
                    if (thkApiRespDo.getRCode() != mErrorCode) {
                        throw new ApiException(thkApiRespDo.getRCode(),
                                thkApiRespDo.getRMessage().toString());
                    }
                    return thkApiRespDo.getResult();
                })
                .compose(mLifecycleTransformer);
    }
}
