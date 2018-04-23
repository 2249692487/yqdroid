package com.ywqln.yqdroid.ui;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.entity.resp.InformationRespDo;
import com.ywqln.yqdroid.presenter.MessagePresenter;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:模仿朋友圈
 */
public class MessageActivity extends BaseActivity {
    @Override
    protected int layoutResId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initComplete() {
        MessagePresenter presenter = new MessagePresenter();
        InformationRespDo informationRespDo = presenter.getMessage();

    }
}
