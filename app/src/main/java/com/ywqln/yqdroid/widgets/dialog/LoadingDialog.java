package com.ywqln.yqdroid.widgets.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.ywqln.yqdroid.R;

/**
 * 描述:自定义dialog
 * <p>
 *
 * @author yanwenqiang
 * @date 2015/6/26.
 */
public class LoadingDialog extends AlertDialog {

    private TextView tipsLoadingMsg;

    private CharSequence mMessage = null;

    public LoadingDialog(Context context) {
        this(context, null);
    }

    public LoadingDialog(Context context, String message) {
        this(context, message, R.style.CentaLoadingDialog);
        if (TextUtils.isEmpty(message)) {
            mMessage = getContext().getResources().getString(
                    R.string.loading);
        } else {
            mMessage = message;
        }
    }

    public LoadingDialog(Context context, String message, int theme) {
        super(context, theme);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_tips_loading);
        tipsLoadingMsg = (TextView) findViewById(R.id.tips_loading_msg);
        tipsLoadingMsg.setText(mMessage);
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessage = message;
        if (tipsLoadingMsg != null) {
            tipsLoadingMsg.setText(message);
        }
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }
}