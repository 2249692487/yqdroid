package com.ywqln.yqdroid.entity.resp;

import com.ywqln.yqdroid.entity.resp.model.CommentModel;

import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:待描述
 */
public class InformationRespDo {
    private List<CommentModel> data;

    public InformationRespDo(List<CommentModel> data) {
        this.data = data;
    }

    public List<CommentModel> getData() {
        return data;
    }

    public void setData(List<CommentModel> data) {
        this.data = data;
    }
}
