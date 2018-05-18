package com.ywqln.yqdroid.entity.resp;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/5/17
 */
public class HerosDo {
    public static final int DECENT = 1;
    public static final int VILLAIN = 0;

    // 英雄名称
    private String name;
    // 英雄拥有的能力
    private String power;
    // 是不是正派角色
    private int decent;
    // 英雄头像
    private String profilePhoto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getDecent() {
        return decent;
    }

    public void setDecent(int decent) {
        this.decent = decent;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
