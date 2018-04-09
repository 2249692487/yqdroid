package com.ywqln.yqdroid.app;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.ywqln.yqdroid.BuildConfig;
import com.ywqln.yqdroid.util.SysUtil;

/**
 * 描述:项目Application {@link Application}
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/11/28
 */
public class App extends Application {

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        // LeakCanary 提供图形界面的内存泄漏，并有堆栈信息
        LeakCanary.install(this);
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().showThreadInfo(false)
                .methodCount(0).tag(BuildConfig.LOGGER_TAG).build();
        // 根据gradle配置来确定Logger的开启关闭
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return !BuildConfig.RELEASE_FLAVOR.equals(BuildConfig.FLAVOR);
            }
        });

        Log.d("yanlina", "appid：" + BuildConfig.APPLICATION_ID);
        Log.d("yanlina", "processName：" + SysUtil.currentProcessName(this));

        if (BuildConfig.APPLICATION_ID.equals(SysUtil.currentProcessName(this))) {
            initClient();
        }
    }

    private void initClient() {
        ApiCreator.init(getApplicationContext());
    }
}
