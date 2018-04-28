package com.ywqln.yqdroid.ui;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;
import com.ywqln.yqdroid.presenter.MessagePresenter;
import com.ywqln.yqdroid.ui.adapter.MessageAdapter;
import com.ywqln.yqdroid.util.StringUtil;

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
    @BindView(R.id.rl_comment_input)
    RelativeLayout rlCommentInput;


    private MessageAdapter adapter;
    private MessagePresenter presenter;
    private boolean editing = false;

    private int rootCommentIndex = -1;
    private int childCommentIndex = -1;

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
        atvComment.setOnFocusChangeListener((view, b) -> {
            if (!b) {
                editing = false;
                atvComment.setText(StringUtil.Empty);
                atvComment.setHint("请输入商品评论");
            }
        });
        atvComment.setOnClickListener(view -> {
            if (!editing) {
                atvComment.setText(StringUtil.Empty);
                atvComment.setHint("请输入商品评论");
            }
        });
        mMessageListView.setOnTouchListener((view, motionEvent) -> {
            rootCommentIndex = -1;
            childCommentIndex = -1;
            atvComment.clearFocus();
            hideSoftInPut(atvComment);
            return false;
        });
    }

    @OnClick(R.id.abtn_comment)
    void commentSubmit(View view) {
        String commentContent = atvComment.getText().toString().trim();
        if (TextUtils.isEmpty(commentContent)) {
            toast("请输入评论内容");
            return;
        }

        String header = "https://avatars1.githubusercontent.com/u/20415227?s=460&v=4";
        CommentModel comment = new CommentModel();
        comment.setProductId("1150");
        comment.setCommId("6");
        comment.setNickname(getCurrentUserName());
        comment.setUserId("666");
        comment.setContent(commentContent);
        comment.setTime(String.valueOf(System.currentTimeMillis()));
        comment.setAvatar(header);

        if (childCommentIndex >= 0) {
            comment.setoNickname(adapter.getDataSource().get(rootCommentIndex).getComment_son().get(
                    childCommentIndex).getNickname());
            adapter.getDataSource().get(rootCommentIndex).getComment_son().add(comment);
        } else if (rootCommentIndex >= 0) {
            comment.setoNickname(adapter.getDataSource().get(rootCommentIndex).getNickname());
            adapter.getDataSource().get(rootCommentIndex).getComment_son().add(comment);
        } else {
            adapter.getDataSource().add(0, comment);
        }

        adapter.notifyDataSetChanged();
        atvComment.clearFocus();
        hideSoftInPut(atvComment);
        rootCommentIndex = -1;
        childCommentIndex = -1;
    }

    @Override
    public void commentClick(View view, MessageAdapter.CommentLevel level) {
        int[] commentIndexs = (int[]) view.getTag();
        rootCommentIndex = commentIndexs[0];
        childCommentIndex = -1;
        CommentModel rootComment = (CommentModel) adapter.getItem(rootCommentIndex);

        String rootCommentId = rootComment.getCommId();
        String childCommentId = StringUtil.Empty;
        String oName = StringUtil.Empty;
        if (level == MessageAdapter.CommentLevel.CHILD) {
            childCommentIndex = commentIndexs[1];
            childCommentId = rootComment.getComment_son().get(childCommentIndex).getCommId();
            oName = rootComment.getComment_son().get(childCommentIndex).getNickname();
        }

        if (oName.equals(getCurrentUserName())) {
            toast("不能回复自己的评论");
            return;
        }

        String msg = "回复[" + rootComment.getNickname() + "]对商品的评论";
        if (level == MessageAdapter.CommentLevel.CHILD) {
            msg = "回复[" + rootComment.getComment_son().get(childCommentIndex).getNickname() + "]对["
                    + rootComment.getComment_son().get(childCommentIndex).getoNickname()
                    + "]的回复评论";
        }

        atvComment.requestFocus();
        atvComment.setHint(msg);
        showSoftInPut(atvComment);
        editing = true;


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

    private String getCurrentUserName() {
        return "燕文强";
    }
}
