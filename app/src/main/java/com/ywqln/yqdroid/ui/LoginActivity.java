package com.ywqln.yqdroid.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.ywqln.yqdroid.widgets.view.StatusBarNotification;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.blurry.Blurry;

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

    @BindView(R.id.logo)
    ImageView logo;

    @BindView(R.id.ll_collection_logo)
    RelativeLayout collectionLogo;

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

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.sqnx);
//        Blurry.with(this)
//                .radius(10)
//                .sampling(20)
//                .color(Color.argb(66, 255, 255, 255))
//                .from(bitmap)
//                .into(logo);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap5 = getBitMBitmap(
                        "http://thirdwx.qlogo"
                                + ".cn/mmopen/vi_32/DYAIOgq83eqW5uArXTCfB9QticvrqriaoPOOZbG7edwS18shArAntdpNNs9E9ZdkibJJ12cPLT7QaUM2AVXZibakHQ/132");

                if (bitmap5 != null) {
                    logo.post(new Runnable() {
                        @Override
                        public void run() {
                            Blurry.with(LoginActivity.this)
                                    .radius(2)
                                    .sampling(2)
                                    .color(Color.argb(66, 255, 255, 255))
                                    .from(bitmap5)
                                    .into(logo);
                        }
                    });
                }
            }
        }).start();
    }


    public Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            in.close();
            Log.e("qln", "map: " + map);
        } catch (Exception e) {
            e.printStackTrace();
            StatusBarNotification notification = new StatusBarNotification.Builder(this)
                    .setMessage("网络状态不给力，加载图片失败")
                    .setTextColor(Color.WHITE)
                    .setBgColor(Color.argb(180, 255, 0, 0))
                    .setDisplayDelayed(2000).build();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notification.show();
                }
            });
        }

        return map;
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

        String androidId = AppUtil.getAndroidId(LoginActivity.this);
        Log.d("androidId", "androidId：" + androidId);

//        if (true) {
//            Intent intent = new Intent(this, MarvelRoleActivity.class);
//            startActivity(intent);
//            return;
//        }

        if (true) {
            Intent intent = new Intent(this, ShowErrorActivity.class);
            startActivity(intent);
            return;
        }

//        if (true) {
//            Intent intent = new Intent(this, MessageActivity.class);
//            startActivity(intent);
//            return;
//        }

//        if (true) {
//            Intent intent = new Intent(this, BackForegroundActivity.class);
//            startActivity(intent);
//            return;
//        }

//        if (true) {
//            Intent intent = new Intent(this, NestedScrollViewActivity.class);
//            startActivity(intent);
//            return;
//        }


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
