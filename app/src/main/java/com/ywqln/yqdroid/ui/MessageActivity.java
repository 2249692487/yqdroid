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
        MessageAdapter.CommentItemClickListener,
        MessagePresenter.NetWorkRequestCallBack {

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

    private String productId = "1150";

    @Override
    protected int layoutResId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {
        presenter = new MessagePresenter(this);
    }

    @Override
    protected void initComplete() {
        presenter.getMessage();

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

        String replyUserId = StringUtil.Empty;
        if (childCommentIndex >= 0) {
            String parentCommId = adapter.getDataSource().get(rootCommentIndex).getCommId();
            replyUserId = adapter.getDataSource().get(rootCommentIndex).getComment_son().get(
                    childCommentIndex).getUserId();
            String replyCommId = adapter.getDataSource().get(rootCommentIndex).getComment_son().get(
                    childCommentIndex).getCommId();
            presenter.addProductComments(productId, parentCommId, replyCommId, replyUserId,
                    commentContent);
            return;
        }
        if (rootCommentIndex >= 0) {
            String parentCommId = adapter.getDataSource().get(rootCommentIndex).getCommId();
            replyUserId = adapter.getDataSource().get(rootCommentIndex).getUserId();
            presenter.addProductComments(productId, parentCommId, null, replyUserId,
                    commentContent);
            return;
        }
        presenter.addProductComments(productId, null, null, replyUserId,
                commentContent);

        /**
         * todo: 你需要在这里灵活发起网络传参数
         * todo: 1.回复【张三】对【李四】的回复评论 --> 必须具备productId, rootCommentId, childCommentId
         * todo: 2.回复【张三】对当前商品的评论 --> 必须具备productId, rootCommentId
         * todo: 3.对当前商品评论 --> 必须具备productId（点击底部'评论'菜单按钮）
         **/
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
         */
    }

    private String getCurrentUserName() {
        return "燕文强";
    }

    @Override
    public void success(CommentModel data) {
        if (childCommentIndex >= 0) {
            adapter.getDataSource().get(rootCommentIndex).getComment_son().add(0, data);
        } else if (rootCommentIndex >= 0) {
            adapter.getDataSource().get(rootCommentIndex).getComment_son().add(0, data);
        } else {
            adapter.getDataSource().add(0, data);
        }

        adapter.notifyDataSetChanged();
        atvComment.clearFocus();
        hideSoftInPut(atvComment);
        rootCommentIndex = -1;
        childCommentIndex = -1;
    }

    @Override
    public void getList(InformationRespDo data) {
        List<CommentModel> commentList = data.getData();
        if (adapter == null) {
            adapter = new MessageAdapter(commentList, this);
            mMessageListView.setAdapter(adapter);
        } else {
            adapter.setDataSource(commentList);
            adapter.notifyDataSetChanged();
        }
    }
}
