package com.ywqln.yqdroid.ui;

import android.widget.ListView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;
import com.ywqln.yqdroid.presenter.MessagePresenter;
import com.ywqln.yqdroid.ui.adapter.MessageAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:模仿
 */
public class MessageActivity extends BaseActivity {

    @BindView(R.id.message_list)
    ListView mMessageListView;

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

        MessageAdapter adapter = new MessageAdapter(commentList);
        mMessageListView.setAdapter(adapter);
    }
}
