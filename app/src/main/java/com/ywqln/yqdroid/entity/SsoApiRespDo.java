package com.ywqln.yqdroid.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 描述：SSO数据返回基类
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/9
 */
public class SsoApiRespDo<T> {

    @SerializedName("ResultNo")
    private int resultNo;
    @SerializedName("Result")
    private T result;
    @SerializedName("Message")
    private String message;
    @SerializedName("Total")
    private int total;

    public int getResultNo() {
        return resultNo;
    }

    public void setResultNo(int resultNo) {
        this.resultNo = resultNo;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
