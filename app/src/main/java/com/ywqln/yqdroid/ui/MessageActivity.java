package com.ywqln.yqdroid.ui;

import android.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ListView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;
import com.ywqln.yqdroid.presenter.MessagePresenter;
import com.ywqln.yqdroid.ui.adapter.MessageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:模仿
 */
public class MessageActivity extends BaseActivity implements
        MessageAdapter.CommentItemClickListener {

    @BindView(R.id.message_list)
    ListView mMessageListView;
    @BindView(R.id.atv_comment)
    AppCompatEditText atvComment;
    @BindView(R.id.abtn_comment)
    AppCompatButton abtnComment;

    private MessageAdapter adapter;
    private MessagePresenter presenter;

    @Override
    protected int layoutResId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {
        presenter = new MessagePresenter();
    }

    @Override
    protected void initComplete() {
        InformationRespDo informationRespDo = presenter.getMessage();
        List<CommentModel> commentList = informationRespDo.getData();

        adapter = new MessageAdapter(commentList, this);
        mMessageListView.setAdapter(adapter);
    }

    @OnClick(R.id.abtn_comment)
    void commentSubmit(View view){
        toast("提交");
    }

    @Override
    public void commentClick(View view, MessageAdapter.CommentLevel level) {
        int[] commentIndexs = (int[]) view.getTag();
        int rootIndex = commentIndexs[0];
        int childIndex = -1;
        CommentModel rootComment = (CommentModel) adapter.getItem(rootIndex);

        String rootCommentId = rootComment.getCommId();
        String childCommentId = "";
        if (level == MessageAdapter.CommentLevel.CHILD) {
            childIndex = commentIndexs[1];
            childCommentId = rootComment.getComment_son().get(childIndex).getCommId();
        }

        String msg = "回复【" + rootComment.getNickname() + "】对当前商品的评论";
        if (level == MessageAdapter.CommentLevel.CHILD) {
            msg = "回复【" + rootComment.getComment_son().get(childIndex).getNickname() + "】对["
                    + rootComment.getNickname()
                    + "]的回复评论";
        }

        new AlertDialog.Builder(view.getContext())
                .setTitle("回复")
                .setMessage(msg)
                .show();

        /**todo: 这里你需要弹出评论的输入框,
         * todo: 点击提交评论的时候，调用P层发出提交请求,
         * todo: 发出提交请求，需要的 产品Id是:productId; 评论Id是:rootCommentId; 子回复评论:childCommentId
         * todo: 请求回调Success方法(在本activity里)里重新请求评论数据 productDetailUIP.productComments(productId);
         *
         * todo: 1.回复【张三】对【李四】的回复评论 --> 必须具备productId, rootCommentId, childCommentId
         * todo: 2.回复【张三】对当前商品的评论 --> 必须具备productId, rootCommentId
         * todo: 3.对当前商品评论 --> 必须具备productId（点击底部'评论'菜单按钮）
         **/
    }
}
