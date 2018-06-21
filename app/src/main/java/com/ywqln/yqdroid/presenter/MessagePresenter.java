package com.ywqln.yqdroid.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;

import java.lang.reflect.Type;
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

    List<CommentModel> commentModelList = null;

    private NetWorkRequestCallBack requestCallBack;

    public NetWorkRequestCallBack getRequestCallBack() {
        return requestCallBack;
    }

    public void setRequestCallBack(NetWorkRequestCallBack requestCallBack) {
        this.requestCallBack = requestCallBack;
    }

    public void getMessage() {
        InformationRespDo respDo = selectDataFromDataBase();

        if (requestCallBack != null) {
            // 模拟服务器返回数据，不做失败的情况了
            requestCallBack.getList(respDo);
        }
    }

    // 模拟从数据库得到数据
    private InformationRespDo selectDataFromDataBase() {

        if (commentModelList == null) {
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
            commentModel1.setUserId("222");
            commentModel1.setContent("嘿嘿");
            commentModel1.setTime(curMill);
            commentModel1.setAvatar(imgTY);

            CommentModel commentModel2 = new CommentModel();
            commentModel2.setProductId("1150");
            commentModel2.setCommId("5");
            commentModel2.setNickname("L");
            commentModel2.setUserId("333");
            commentModel2.setContent("嗨");
            commentModel2.setTime(curMill);
            commentModel2.setAvatar(imgLJ);


            CommentModel commentModel3 = new CommentModel();
            commentModel3.setProductId("1150");
            commentModel3.setCommId("1");
            commentModel3.setNickname("微凉");
            commentModel3.setUserId("222");
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
            commentChild1.setReplay_userid("333");
            commentChild1.setTime(curMill);
            commentChild1.setAvatar(imgTY);

            CommentModel commentChild2 = new CommentModel();
            commentChild2.setProductId("1150");
            commentChild2.setCommId("2");
            commentChild2.setNickname("L");
            commentChild2.setUserId("359");
            commentChild2.setContent("嘻嘻");
            commentChild2.setoNickname("微凉");
            commentChild1.setReplay_userid("222");
            commentChild2.setTime(curMill);
            commentChild2.setAvatar(imgLJ);

            commentModel3.getComment_son().add(commentChild2);
            commentModel3.getComment_son().add(commentChild1);
            commentModel3.getComment_son().add(commentChild2);
            commentModel3.getComment_son().add(commentChild2);
            commentModel3.getComment_son().add(commentChild2);
            commentModel3.getComment_son().add(commentChild2);

            commentModelList = new ArrayList<>();
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
        }

        return new InformationRespDo(commentModelList);
    }

    public interface NetWorkRequestCallBack {
        void success(CommentModel data);

        void getList(InformationRespDo data);
    }


    public void addProductComments(String productKeyId, String parentCommId, String replyCommId,String replyUserId,
            String content) {
        if (requestCallBack != null) {
            // 模拟服务器返回数据，不做失败的情况了
            String header = "https://avatars1.githubusercontent.com/u/20415227?s=460&v=4";
            CommentModel comment = new CommentModel();
            comment.setProductId(productKeyId);
            comment.setUserId("666");
            comment.setNickname("燕文强");
            comment.setContent(content);
            comment.setAvatar(header);
            comment.setTime(String.valueOf(System.currentTimeMillis()));

            // 评论商品（只有productKeyId）
            if (TextUtils.isEmpty(parentCommId)) {
                // 随便设置一个Id
                comment.setCommId("6");
                requestCallBack.success(comment);
                return;
            }
            List<CommentModel> respList = line(selectDataFromDataBase());
            // 对商品的某条评论进行回复（productKeyId & parentCommId）
            if (TextUtils.isEmpty(replyCommId)) {
                for (CommentModel item : respList) {
                    if (item.getUserId().equals(replyUserId)) {
                        comment.setCommId("6");
                        comment.setoNickname(item.getNickname());
                        comment.setReplay_userid(item.getUserId());
                        break;
                    }
                }
                requestCallBack.success(comment);
                return;
            }

            // 对回复某条评论进行回复的内容进行回复（productKeyId & parentCommId & replyUserId）
            for (CommentModel item : respList) {
                if (item.getUserId().equals(replyUserId)) {
                    comment.setCommId("6");
                    comment.setoNickname(item.getNickname());
                    comment.setReplay_userid(item.getUserId());
                    break;
                }
            }
            requestCallBack.success(comment);
        }
    }

    private List<CommentModel> line(InformationRespDo value) {
        List<CommentModel> content = value.getData();

        String comJson = new Gson().toJson(content);
        Type classType = new TypeToken<List<CommentModel>>() {
        }.getType();
        List<CommentModel> comment2 = new Gson().fromJson(comJson, classType);

        String couponArr = new Gson().toJson(comment2);

        for (CommentModel item : content) {
            if (item.getComment_son().size() > 0) {
                for (CommentModel subItem : item.getComment_son()) {
                    comment2.add(subItem);
                }
            }
        }

        return comment2;
    }
}
