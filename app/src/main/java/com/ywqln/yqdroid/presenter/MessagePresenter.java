package com.ywqln.yqdroid.presenter;

import com.google.gson.Gson;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:待描述
 */
public class MessagePresenter {
    public MessagePresenter(NetWorkRequestCallBack requestCallBack) {
        this.requestCallBack = requestCallBack;
    }

    private NetWorkRequestCallBack requestCallBack;

    public NetWorkRequestCallBack getRequestCallBack() {
        return requestCallBack;
    }

    public void setRequestCallBack(NetWorkRequestCallBack requestCallBack) {
        this.requestCallBack = requestCallBack;
    }

    // 模拟网络请求
    public void addProductComments(CommentModel comment) {
        if (requestCallBack != null) {
            // 模拟服务器返回数据，不做失败的情况了
            requestCallBack.success(comment);
        }
    }

    public void getMessage() {
        String imgTY =
                "https://imgsa.baidu"
                        + ".com/zhixin/abpic/item/29752a9b033b5bb5c6db323834d3d539b700bcac.jpg";
        String imgLJ =
                "https://imgsa.baidu"
                        + ".com/zhixin/abpic/item/48151723dd54564eeeb2d651b1de9c82d0584f47.jpg";
        String curMill = String.valueOf(System.currentTimeMillis());


        CommentModel commentModel1 = new CommentModel();
        commentModel1.setProductId("1150");
        commentModel1.setCommId("4");
        commentModel1.setNickname("微凉");
        commentModel1.setUserId("457");
        commentModel1.setContent("嘿嘿");
        commentModel1.setTime(curMill);
        commentModel1.setAvatar(imgTY);

        CommentModel commentModel2 = new CommentModel();
        commentModel2.setProductId("1150");
        commentModel2.setCommId("5");
        commentModel2.setNickname("L");
        commentModel2.setUserId("457");
        commentModel2.setContent("嗨");
        commentModel2.setTime(curMill);
        commentModel2.setAvatar(imgLJ);


        CommentModel commentModel3 = new CommentModel();
        commentModel3.setProductId("1150");
        commentModel3.setCommId("1");
        commentModel3.setNickname("微凉");
        commentModel3.setUserId("457");
        commentModel3.setContent("你好");
        commentModel3.setTime(curMill);
        commentModel3.setAvatar(imgTY);

        CommentModel commentChild1 = new CommentModel();
        commentChild1.setProductId("1150");
        commentChild1.setCommId("3");
        commentChild1.setNickname("微凉");
        commentChild1.setUserId("457");
        commentChild1.setContent("你好");
        commentChild1.setoNickname("L");
        commentChild1.setTime(curMill);
        commentChild1.setAvatar(imgTY);

        CommentModel commentChild2 = new CommentModel();
        commentChild2.setProductId("1150");
        commentChild2.setCommId("2");
        commentChild2.setNickname("L");
        commentChild2.setUserId("359");
        commentChild2.setContent("嘻嘻");
        commentChild2.setoNickname("微凉");
        commentChild2.setTime(curMill);
        commentChild2.setAvatar(imgLJ);

        commentModel3.getComment_son().add(commentChild2);
        commentModel3.getComment_son().add(commentChild1);
        commentModel3.getComment_son().add(commentChild2);
        commentModel3.getComment_son().add(commentChild2);
        commentModel3.getComment_son().add(commentChild2);
        commentModel3.getComment_son().add(commentChild2);

        List<CommentModel> commentModelList = new ArrayList<>();
        commentModelList.add(commentModel1);
        commentModelList.add(commentModel2);
        commentModelList.add(commentModel3);

        Gson gson = new Gson();
        for (int i = 0; i < 30; i++) {
            String com2Json = gson.toJson(commentModel2);
            String com3Json = gson.toJson(commentModel3);

            CommentModel comment2 = gson.fromJson(com2Json, CommentModel.class);
            CommentModel comment3 = gson.fromJson(com3Json, CommentModel.class);

            commentModelList.add(comment2);
            commentModelList.add(comment3);
        }

        for (CommentModel item : commentModelList) {
            if (item.getComment_son().size() > 3) {
                item.setColsed(true);
            }
        }

        InformationRespDo respDo = new InformationRespDo(commentModelList);

        if (requestCallBack != null) {
            // 模拟服务器返回数据，不做失败的情况了
            requestCallBack.getList(respDo);
        }
    }

    public interface NetWorkRequestCallBack {
        void success(CommentModel data);

        void getList(InformationRespDo data);
    }
}
