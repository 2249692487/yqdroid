package com.ywqln.yqdroid.widgets.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 描述：可嵌套的ScrollView
 * <p>
 *
 * @author yanwenqiang
 * @date 2018/4/8
 */
public class FixScrollView extends ScrollView {

    private int innerScrollCount = 0;

    public FixScrollView(Context context) {
        super(context);
    }

    public FixScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FixScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        innerScrollCount = 0;
        int childCount = getChildCount();
        ScrollView innerScrollView = null;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            try {
                innerScrollView = (ScrollView) view;
                innerScrollCount++;
            } catch (ClassCastException ex) {
            }
        }
        if (innerScrollCount > 0) {
            if (innerScrollView != null) {
                innerScrollView.getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.onTouchEvent(ev);
    }
}
