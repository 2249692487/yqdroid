package com.ywqln.yqdroid.ui;

import android.graphics.Color;
import android.widget.Button;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
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

    private StatusBarNotification.Builder mBuilder;
    private StatusBarNotification mStatusBarNotification;

    @Override
    protected int layoutResId() {
        return R.layout.activity_showerror;
    }

    @Override
    protected void initViews() {
        mStatusBarNotification = new StatusBarNotification.Builder(this)
                .setMessage("当前网络状态不佳，可下拉刷新尝试")
                .setTextColor(Color.BLUE)
                .setBgColor(Color.argb(120, 0, 255, 0))
                .setDisplayDelayed(2000)
                .show();
    }

    @Override
    protected void initComplete() {
        clickButton.setOnClickListener(view -> {
            btn_click();
        });
    }

    private void btn_click() {

//        new StatusBarNotification.Builder(this)
//                .setMessage("当前网络状态不佳，可下拉刷新尝试")
//                .setTextColor(Color.BLUE)
//                .setBgColor(Color.argb(120, 0, 255, 0))
//                .setDisplayDelayed(2000)
//                .show();
//

        mStatusBarNotification.setTextColor(Color.RED);
        mStatusBarNotification.setDisplayDelayed(3000);
        mStatusBarNotification.setVerticalMargin(20);
        mStatusBarNotification.show();

    }
}
