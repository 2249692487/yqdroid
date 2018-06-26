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

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:顶部通知.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/6/26
 */
public class StatusBarNotification implements NotificationInterface {
    private Builder mBuilder;
//    private LinearLayout mContainer;
//    private TextView mMessageTextView;

    private List<LinearLayout> mContainerList;

    protected StatusBarNotification(Activity activity) {
        mContainerList = new ArrayList<>();
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
        gone();
        // window级别，自上而下出来
        LinearLayout container = new LinearLayout(mBuilder.mActivity);
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
        container.setGravity(Gravity.CENTER);
        mBuilder.mActivity.getWindow().addContentView(container, layoutParams);


        WindowManager.LayoutParams textViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                0, 0,
                PixelFormat.TRANSPARENT
        );
        TextView messageTextView = new TextView(mBuilder.mActivity);
        messageTextView.setTag("msgView");
        messageTextView.setBackgroundColor(mBuilder.bgColor);
        messageTextView.setTextColor(mBuilder.textColor);
        messageTextView.setGravity(Gravity.CENTER);
        messageTextView.setTextSize(mBuilder.textSize);
        messageTextView.setPadding(0, mBuilder.verticalMargin, 0, mBuilder.verticalMargin);
        messageTextView.setText(mBuilder.getMessage());
        container.addView(messageTextView, textViewParam);
        // 从上而下的动画
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(mBuilder.animationDuration);
        messageTextView.startAnimation(animation);

        mContainerList.add(container);

        container.setTag(true);
        container.postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
                );
                animation.setDuration(mBuilder.animationDuration);
                messageTextView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        container.setTag(false);
                        container.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, mBuilder.displayDelayed);
    }

    @Override
    public void dismiss() {

        int count = mContainerList.size();
        for (int i = 0; i < count; i++) {
            LinearLayout container = mContainerList.get(i);
            boolean isShow = (boolean) container.getTag();
            if (isShow) {
                TranslateAnimation animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f
                );
                TextView messageTextView = container.findViewWithTag("msgView");
                animation.setDuration(mBuilder.animationDuration);
                messageTextView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        container.setVisibility(View.GONE);
                        container.setTag(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    @Override
    public void gone() {
        int count = mContainerList.size();
        for (int i = 0; i < count; i++) {
            LinearLayout container = mContainerList.get(i);
            boolean isShow = (boolean) container.getTag();
            if (isShow) {
                container.setVisibility(View.GONE);
            }
        }
    }

    public static class Builder {
        private Activity mActivity;
        private StatusBarNotification mNotification;

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

        public StatusBarNotification build() {
            if (mNotification == null) {
                mNotification = new StatusBarNotification(mActivity);
                mNotification.setBuilder(this);
            }
            return mNotification;
        }

        public StatusBarNotification show() {
            if (mNotification == null) {
                mNotification = new StatusBarNotification(mActivity);
                mNotification.setBuilder(this);
            }
            mNotification.gone();
            mNotification.show();
            return mNotification;
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
