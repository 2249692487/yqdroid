package com.ywqln.yqdroid.ui;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywqln.yqdroid.BuildConfig;
import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.apiservice.UserApi;
import com.ywqln.yqdroid.app.ApiCreator;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.constant.SprfConstant;
import com.ywqln.yqdroid.constant.SsoConstant;
import com.ywqln.yqdroid.entity.req.StaffLoginReqDo;
import com.ywqln.yqdroid.entity.resp.LoginRespDo;
import com.ywqln.yqdroid.rxjava.ApiException;
import com.ywqln.yqdroid.rxjava.ResponseObserver;
import com.ywqln.yqdroid.util.AppUtil;
import com.ywqln.yqdroid.util.MD5Util;
import com.ywqln.yqdroid.util.SprfUtil;
import com.ywqln.yqdroid.util.StringUtil;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * 描述:登录界面
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/1
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_account)
    EditText mEditAccount;
    @BindView(R.id.et_password)
    EditText mEditPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.iv_password_show)
    ImageView mIvPasswordShow;
    @BindView(R.id.tv_version_code)
    TextView mTvVersionCode;

    /**
     * 密码输入状态初始值
     */
    private boolean INPUT_TYPE_FLAG = true;
    /**
     * 双击退出时间初始值
     */
    private long exitTime = 0;

    @Override
    protected int layoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initComplete() {
        mTvVersionCode.setText(BuildConfig.VERSION_NAME);
        mIvPasswordShow.setOnClickListener(view -> passwordVisibleClick());
        mBtnLogin.setOnClickListener(view -> loginClick());
    }

    /**
     * 密码显示隐藏
     */
    private void passwordVisibleClick() {
        if (INPUT_TYPE_FLAG) {
            INPUT_TYPE_FLAG = false;
            mEditPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            mEditPassword.setSelection(mEditPassword.getText().length());
            mIvPasswordShow.setImageResource(R.drawable.lock_btn_eye_open);
        } else {
            INPUT_TYPE_FLAG = true;
            mEditPassword.setInputType(
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mEditPassword.setSelection(mEditPassword.getText().length());
            mIvPasswordShow.setImageResource(R.drawable.lock_btn_eye_close);
        }
    }

    private void loginClick() {

        String deviceId = AppUtil.getDevicesId(LoginActivity.this);
        Log.d("deviceId", "devicesId：" + deviceId);


        if (true) {
            Intent intent = new Intent(this, NestedScrollViewActivity.class);
            startActivity(intent);
            return;
        }


        String account = mEditAccount.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        boolean approved = verificationAccountAndPassword(account, password);
        String devicesId = AppUtil.getDevicesId(LoginActivity.this);
        if (!approved) {
            return;
        }
        if (StringUtil.isNullOrEmpty(devicesId)) {
            return;
        }
        loadingDialog();
        String sign = MD5Util.md5(
                SsoConstant.APP_NO_SECRET_KEY + account + password + SsoConstant.SECRET_KEY);
        ApiCreator.create(UserApi.class)
                .login(new StaffLoginReqDo(SsoConstant.APP_NO, account, password, sign))
                .compose(this.applySchedulers())
                .compose(this.bindSSOTransformer())
                .subscribe(new ResponseObserver<LoginRespDo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginRespDo loginRespDo) {
                        saveUserInfo(loginRespDo, devicesId);
                        toast("登录成功！");
//                        startActivity(new Intent(LoginActivity.this, FaceInfoActivity.class));
//                        finish();
                    }

                    @Override
                    public void onComplete() {
                        cancelLoadingDialog();
                    }

                    @Override
                    public void error(ApiException e) {
                        cancelLoadingDialog();
                        toast(e.message);
                    }
                });
    }

    /**
     * 验证用户名密码
     */
    private boolean verificationAccountAndPassword(String account, String password) {
        if (TextUtils.isEmpty(account)) {
            toast(getString(R.string.please_enter_the_domain_account));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            toast(getString(R.string.please_enter_the_password));
            return false;
        }
        return true;
    }

    /**
     * 保存用户登录的信息
     */
    private void saveUserInfo(LoginRespDo loginRespDo, String devicesId) {
        SprfUtil.setString(LoginActivity.this, SprfConstant.HK_SESSION, loginRespDo.getSessionId());
        SprfUtil.setString(LoginActivity.this, SprfConstant.CITY_CODE,
                loginRespDo.getStaff().getCompanyCode());
        SprfUtil.setString(LoginActivity.this, SprfConstant.COMPANY_NAME,
                loginRespDo.getStaff().getCompanyCode());
        SprfUtil.setString(LoginActivity.this, SprfConstant.STAFF_NO,
                loginRespDo.getStaff().getStaffNo());
        SprfUtil.setString(LoginActivity.this, SprfConstant.UDID, devicesId);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 点击返回键退出的间隔时间
            int intervalTime = 2000;
            if ((System.currentTimeMillis() - exitTime) > intervalTime) {
                toast(getString(R.string.again_according_to_the_exit));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
