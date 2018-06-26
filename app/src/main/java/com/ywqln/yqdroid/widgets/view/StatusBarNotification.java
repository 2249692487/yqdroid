package com.ywqln.yqdroid.widgets.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 描述:顶部通知.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/6/26
 */
public class StatusBarNotification implements NotificationInterface {
    private Builder mBuilder;
    private LinearLayout mContainer;
    private TextView mMessageTextView;

    protected StatusBarNotification(Activity activity) {
        mBuilder = new Builder(activity);
    }

    protected void setBuilder(Builder builder) {
        mBuilder = builder;
    }

    public void setMessage(String message) {
        mBuilder.message = message;
    }

    public void setTextSize(float textSize) {
        mBuilder.textSize = textSize;
    }

    public void setTextColor(int color) {
        mBuilder.setTextColor(color);
    }

    public void setBgColor(int color) {
        mBuilder.setBgColor(color);
    }

    public void setDisplayDelayed(long displayDelayed) {
        mBuilder.displayDelayed = displayDelayed;
    }

    public void setAnimationDuration(long animationDuration) {
        mBuilder.animationDuration = animationDuration;
    }

    public void setVerticalMargin(int verticalMargin) {
        mBuilder.verticalMargin = verticalMargin;
    }

    public void show() {
        // window级别，自上而下出来
        mContainer = new LinearLayout(mBuilder.mActivity);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        // flag 设置 Window 属性
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        // type 设置 Window 类别（层级）
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        layoutParams.gravity = Gravity.TOP;
        mContainer.setGravity(Gravity.CENTER);
        mBuilder.mActivity.getWindow().addContentView(mContainer, layoutParams);


        WindowManager.LayoutParams textViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        mMessageTextView = new TextView(mBuilder.mActivity);
        mMessageTextView.setBackgroundColor(mBuilder.bgColor);
        mMessageTextView.setTextColor(mBuilder.textColor);
        mMessageTextView.setGravity(Gravity.CENTER);
        mMessageTextView.setTextSize(mBuilder.textSize);
        mMessageTextView.setPadding(0, mBuilder.verticalMargin, 0, mBuilder.verticalMargin);
        mMessageTextView.setText(mBuilder.getMessage());
        mContainer.addView(mMessageTextView, textViewParam);
        // 从上而下的动画
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(mBuilder.animationDuration);
        mMessageTextView.startAnimation(animation);

        mContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, mBuilder.displayDelayed);
    }

    @Override
    public void dismiss() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
        );
        animation.setDuration(mBuilder.animationDuration);
        mMessageTextView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void gone() {
        mContainer.setVisibility(View.GONE);
    }

    public static class Builder {
        private Activity mActivity;

        private String message = "";
        private float textSize = 12;
        private int textColor = Color.WHITE;
        private int bgColor = Color.RED;
        private long displayDelayed = 2000;
        private long animationDuration = 200;
        private int verticalMargin = 10;

        public Builder(Activity activity) {
            this.mActivity = activity;
        }

        public StatusBarNotification show() {
            StatusBarNotification notification = new StatusBarNotification(mActivity);
            notification.setBuilder(this);
            notification.show();
            return notification;
        }

        public String getMessage() {
            return message;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public float getTextSize() {
            return textSize;
        }

        public Builder setTextSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        public int getTextColor() {
            return textColor;
        }

        public Builder setTextColor(int color) {
            this.textColor = color;
            return this;
        }

        public int getBgColor() {
            return bgColor;
        }

        public Builder setBgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public long getDisplayDelayed() {
            return displayDelayed;
        }

        public Builder setDisplayDelayed(long displayDelayed) {
            this.displayDelayed = displayDelayed;
            return this;
        }

        public long getAnimationDuration() {
            return animationDuration;
        }

        public Builder setAnimationDuration(long animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public int getVerticalMargin() {
            return verticalMargin;
        }

        public Builder setVerticalMargin(int verticalMargin) {
            this.verticalMargin = verticalMargin;
            return this;
        }
    }
}
