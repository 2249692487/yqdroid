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

    private StatusBarNotification mNotification;

    @Override
    protected int layoutResId() {
        return R.layout.activity_showerror;
    }

    @Override
    protected void initViews() {
        mNotification = new StatusBarNotification.Builder(this)
                .setMessage("这是提示消息")
                .setTextColor(Color.WHITE)
                .setBgColor(Color.argb(180, 255, 0, 0))
                .setDisplayDelayed(2000).build();

        mNotification.show();
    }

    @Override
    protected void initComplete() {
        clickButton.setOnClickListener(view -> {
            btn_click();
        });
    }

    int count = 0;

    private void btn_click() {
        mNotification.setMessage("当前网络状态不佳，可下拉刷新尝试");
        mNotification.show();

//        new StatusBarNotification.Builder(this)
//                .setMessage("当前网络状态不佳，可下拉刷新尝试")
//                .setTextColor(Color.WHITE)
//                .setBgColor(Color.argb(180, 0, 255, 0))
//                .setDisplayDelayed(2000).show();

//        count = 0;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    count++;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mNotification.setMessage("这是提示消息" + count);
//                            mNotification.show();
//                        }
//                    });
//                }
//            }
//        }).start();

    }
}
