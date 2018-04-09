package com.ywqln.yqdroid.entity.resp;

import com.ywqln.yqdroid.entity.resp.model.StaffModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 描述:登录响应Do
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public class LoginRespDo {
    @SerializedName("SessionId")
    private String sessionId;
    @SerializedName("Staff")
    private StaffModel staff;
    @SerializedName("Staffs")
    private List<StaffModel> staffs;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }

    public List<StaffModel> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffModel> staffs) {
        this.staffs = staffs;
    }
}
