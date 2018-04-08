package com.ywqln.yqdroid.ui;

import android.widget.ScrollView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;

import butterknife.BindView;

/**
 * 描述：嵌套ScrollView
 * <p>
 *
 * @author yanwenqiang
 * @date 2018/4/8
 */
public class NestedScrollViewActivity extends BaseActivity {

    @BindView(R.id.boxScrollView)
    public ScrollView boxScrollView;

    @BindView(R.id.itemScrollView)
    public ScrollView itemScrollView;


    @Override
    protected int layoutResId() {
        return R.layout.activity_nested_scrollview;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initComplete() {

//        boxScrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                itemScrollView.getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });
//
//        itemScrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

    }
}
