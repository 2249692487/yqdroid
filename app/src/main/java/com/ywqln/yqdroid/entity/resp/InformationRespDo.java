package com.ywqln.yqdroid.entity.resp;

import com.ywqln.yqdroid.entity.resp.model.InformationModel;

import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:待描述
 */
public class InformationRespDo {
    private List<InformationModel> data;

    public List<InformationModel> getData() {
        return data;
    }

    public void setData(List<InformationModel> data) {
        this.data = data;
    }
}
