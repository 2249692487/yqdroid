package com.ywqln.yqdroid.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.rxjava.HkTransformer;
import com.ywqln.yqdroid.rxjava.SsoTransformer;
import com.ywqln.yqdroid.util.StringUtil;
import com.ywqln.yqdroid.widgets.dialog.LoadingDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 基础{@link android.app.Activity}
 *
 * @author yanwenqiang
 * @date 2017/11/28
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    /**
     * 加载框
     */
    protected AlertDialog loadingDialog;
    /**
     * toast
     */
    protected Toast toast;

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        overrideConfiguration.setToDefaults();
        return super.createConfigurationContext(overrideConfiguration);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        initComplete();
    }

    /**
     * 布局id
     *
     * @return int 布局id
     */
    protected abstract @LayoutRes
    int layoutResId();

    /**
     * initViews
     */
    protected abstract void initViews();

    /**
     * 初始化完成
     */
    protected abstract void initComplete();

    /**
     * 设置{@link android.support.v7.widget.Toolbar},并添加返回按钮
     */
    @SuppressWarnings("unused")
    protected void setToolbarTitle() {
        setToolbarTitle(null, true);
    }

    /**
     * 设置{@link android.support.v7.widget.Toolbar},并添加返回按钮
     *
     * @param id String标题资源
     */
    @SuppressWarnings("unused")
    protected void setToolbarTitle(@StringRes int id) {
        setToolbarTitle(id, true);
    }

    /**
     * 设置{@link android.support.v7.widget.Toolbar}
     *
     * @param id           String标题资源
     * @param showHomeAsUp 返回按钮
     */
    protected void setToolbarTitle(@StringRes int id, boolean showHomeAsUp) {
        setToolbarTitle(getString(id), showHomeAsUp);
    }

    /**
     * 设置{@link android.support.v7.widget.Toolbar},并设置返回按钮
     *
     * @param title        标题
     * @param showHomeAsUp 返回按钮
     */
    protected void setToolbarTitle(CharSequence title, boolean showHomeAsUp) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
            actionBar.setTitle(title);
        }
    }

    /**
     * 更新标题，调用{@link #setToolbarTitle()}、{@link #setToolbarTitle(int)}、{@link
     * #setToolbarTitle(CharSequence, boolean)}后有效，
     * 或者调用{@link #setSupportActionBar(Toolbar)}后有效
     *
     * @param title 标题
     */
    @SuppressWarnings("unused")
    protected void updateTitle(CharSequence title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 调度器转化
     */
    public final <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 处理响应逻辑,只关注content
     */
    public final <T> HkTransformer<T> bindHkTransformer() {
        return new HkTransformer<>(this.<T>bindUntilEvent(ActivityEvent.DESTROY));
    }

    /**
     * 处理响应逻辑,只关注content
     */
    public final <T> SsoTransformer<T> bindSSOTransformer() {
        return new SsoTransformer<>(this.<T>bindUntilEvent(ActivityEvent.DESTROY));
    }

    protected void loadingDialog() {
        loadingDialog(StringUtil.Empty);
    }

    /**
     * 自定义加载框
     */
    protected void loadingDialog(String message) {
        loadingDialog(message, false);
    }

    /**
     * 自定义加载框
     *
     * @param message    内容
     * @param cancelable 是否取消
     */
    protected void loadingDialog(String message, boolean cancelable) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!TextUtils.isEmpty(message)) {
            loadingDialog.setMessage(message);
        }
        loadingDialog.setCancelable(cancelable);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 取消加载框
     */
    protected void cancelLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    /**
     * Toast统一入口
     */
    protected void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    /**
     * 隐藏键盘
     */
    protected void hideSoftInPut(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
