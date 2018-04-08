package com.ywqln.yqdroid.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 描述:HK请求Do
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public class HkApiRespDo<T> {
    @SerializedName("RCode")
    private int rCode;
    @SerializedName("RMessage")
    private Object rMessage;
    @SerializedName("Result")
    private T result;
    @SerializedName("Total")
    private int total;

    public int getRCode() {
        return rCode;
    }

    public void setRCode(int rCode) {
        this.rCode = rCode;
    }

    public Object getRMessage() {
        return rMessage;
    }

    public void setRMessage(Object rMessage) {
        this.rMessage = rMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "HkApiRespDo{" +
                "RCode=" + rCode +
                ", rMessage=" + rMessage +
                ", result=" + result +
                ", total=" + total +
                '}';
    }
}
