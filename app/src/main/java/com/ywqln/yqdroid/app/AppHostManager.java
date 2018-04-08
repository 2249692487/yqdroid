package com.ywqln.yqdroid.app;

import com.ywqln.yqdroid.BuildConfig;
import com.ywqln.yqdroid.apiservice.UserApi;
import com.ywqln.yqdroid.util.StringUtil;

/**
 * Created by yanwenqiang on 2017/12/3.
 * <p>
 * 描述:管理应用中请求主机名
 */

public class AppHostManager implements ApiCreator.HostManagerCallBack {

    @Override
    public String intercept(Class clazz) {
        // 通过Api定义Class来确定应该用哪个HostUrl
        if(clazz.equals(UserApi.class)){
            return BuildConfig.SSO_HOST;
        }
        return StringUtil.Empty;
    }
}
