package com.ywqln.yqdroid.entity.req;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yanwenqiang on 2017/12/1.
 * <p>
 * 描述:登录请求
 * @author yanwenqiang
 */
public class StaffLoginReqDo {

    @SerializedName("AppNo")
    private String appNo;
    @SerializedName("Account")
    private String account;
    @SerializedName("Password")
    private String password;
    @SerializedName("Sign")
    private String sign;

    public StaffLoginReqDo(String appNo, String account, String password,String sign) {
        this.appNo = appNo;
        this.account = account;
        this.password = password;
        this.sign = sign;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

