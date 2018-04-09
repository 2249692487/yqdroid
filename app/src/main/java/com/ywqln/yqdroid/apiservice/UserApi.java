package com.ywqln.yqdroid.apiservice;

import com.ywqln.yqdroid.entity.SsoApiRespDo;
import com.ywqln.yqdroid.entity.req.StaffLoginReqDo;
import com.ywqln.yqdroid.entity.resp.LoginRespDo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 描述：用户相关api
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public interface UserApi {

    /**
     * 登录
     */
    @POST("json/reply/TokenRequest")
    Observable<SsoApiRespDo<LoginRespDo>> login(@Body StaffLoginReqDo StaffLoginReqDo);

}
