package com.ywqln.yqdroid.presenter;

import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;
import com.ywqln.yqdroid.entity.resp.model.InformationModel;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:待描述
 */
public class MessagePresenter {
    public InformationRespDo getMessage(){
        InformationRespDo respDo = new InformationRespDo();
        InformationModel information = new InformationModel();
        InformationModel information2 = new InformationModel();

        CommentModel commentModel = new CommentModel();
        commentModel.setProductId("1150");
        commentModel.setCommId("4");
        commentModel.setContent("嘿嘿");
        commentModel.setNickname("微凉");
        commentModel.setUserId("457");



        return respDo;
    }
}
