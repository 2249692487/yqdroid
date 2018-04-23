package com.ywqln.yqdroid.entity.resp.model;

import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:消息实体
 */
public class CommentModel {
    private String productId;
    private String commId;
    private String nickname;
    private String userId;
    private String time;
    // 回复人的id
    private String replay_userid;
    private String content;
    private String avatar;
    // 回复的消息
    private List<CommentModel> comment_son;
    // 回复名字
    private String oNickname;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReplay_userid() {
        return replay_userid;
    }

    public void setReplay_userid(String replay_userid) {
        this.replay_userid = replay_userid;
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

    public List<CommentModel> getComment_son() {
        return comment_son;
    }

    public void setComment_son(List<CommentModel> comment_son) {
        this.comment_son = comment_son;
    }

    public String getoNickname() {
        return oNickname;
    }

    public void setoNickname(String oNickname) {
        this.oNickname = oNickname;
    }
}
