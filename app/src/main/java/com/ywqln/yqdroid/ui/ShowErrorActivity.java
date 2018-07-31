package com.ywqln.yqdroid.ui;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.util.MultiTapUtil;
import com.ywqln.yqdroid.widgets.view.StatusBarNotification;

import butterknife.BindView;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/6/26
 */
public class ShowErrorActivity extends BaseActivity {

    @BindView(R.id.clickButton)
    public Button clickButton;

    @BindView(R.id.imageView)
    public ImageView imageView;

    private StatusBarNotification mNotification;

    @Override
    protected int layoutResId() {
        return R.layout.activity_showerror;
    }

    @Override
    protected void initViews() {
        // 方式1
        mNotification = new StatusBarNotification.Builder(this)
                .setMessage("这是提示消息")
                .setTextColor(Color.WHITE)
                .setBgColor(Color.argb(180, 255, 0, 0))
                .setDisplayDelayed(2000).build();

        mNotification.show();
    }

    @Override
    protected void initComplete() {
        clickButton.setOnClickListener(view -> btn_click());

        imageView.setOnClickListener(new MultiTapUtil()
                .setInterval(300)
                .setTriggerCount(5)
                .setMultiTapListener(new MultiTapUtil.MultiTapListener() {
                    @Override
                    public void multiClick(View view) {
                        new StatusBarNotification.Builder(ShowErrorActivity.this)
                                .setMessage("欢迎管理员")
                                .show();
                    }
                }));

    }

    private void btn_click() {
        mNotification.setMessage("当前网络状态不佳，可下拉刷新尝试");
        mNotification.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();


        // 方式2
//        new StatusBarNotification.Builder(this)
//                .setMessage("当前网络状态不佳，可下拉刷新尝试")
//                .setTextColor(Color.WHITE)
//                .setBgColor(Color.argb(180, 0, 255, 0))
//                .setDisplayDelayed(2000).show();

    }
}
