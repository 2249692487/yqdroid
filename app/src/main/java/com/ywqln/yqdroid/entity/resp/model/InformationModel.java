package com.ywqln.yqdroid.entity.resp.model;

import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:朋友圈动态实体
 */
public class InformationModel {
    private String nickname;
    private String time;
    private String content;
    private String avatar;
    private List<CommentModel> mCommentModelList;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<CommentModel> getCommentModelList() {
        return mCommentModelList;
    }

    public void setCommentModelList(
            List<CommentModel> commentModelList) {
        mCommentModelList = commentModelList;
    }
}
