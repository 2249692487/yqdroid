package com.ywqln.yqdroid.util;

import android.view.View;

/**
 * 描述:多次连续点击触发器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/7/26
 */
public class MultiTapUtil implements View.OnClickListener {
    private MultiTapListener mMultiTapListener;
    private android.os.Handler mHandler = new android.os.Handler();
    private int mSum = 0;
    private int triggerCount = 5;
    private long mLastTime = 0;
    private long interval = 300;

    public MultiTapUtil() {
    }

    public MultiTapUtil(MultiTapListener multiTapListener) {
        mMultiTapListener = multiTapListener;
    }

    public MultiTapUtil setMultiTapListener(MultiTapListener multiTapListener) {
        mMultiTapListener = multiTapListener;
        return this;
    }

    public MultiTapUtil setTriggerCount(int triggerCount) {
        this.triggerCount = triggerCount;
        return this;
    }

    public MultiTapUtil setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if (mSum == 0) {
            mLastTime = currentTime - interval;
        }
        long duration = currentTime - mLastTime;
        if (duration <= interval) {
            mSum++;
        }
        mLastTime = currentTime;
        mHandler.postDelayed(() -> {
            long delayDuration = System.currentTimeMillis() - mLastTime;
            if (delayDuration >= interval) {
                mSum = 0;
            }
        }, interval);
        if (mSum >= triggerCount) {
            mSum = 0;
            mMultiTapListener.multiClick(view);
        }
    }

    public interface MultiTapListener {
        void multiClick(View view);
    }
}
