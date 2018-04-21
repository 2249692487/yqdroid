package com.ywqln.yqdroid.widgets.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.ywqln.yqdroid.R;

/**
 * Created by yanwenqiang on 2018/4/21.
 * <p>
 * 描述:圆角图片视图
 */
public class RoundCornerImageView extends AppCompatImageView {
    private final RectF roundRect = new RectF();
    private float rect_adius = 100;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    private RoundType mRoundType = RoundType.ALL;

    public RoundType getRoundType() {
        return mRoundType;
    }

    public void setRoundType(RoundType roundType) {
        mRoundType = roundType;
    }

    public enum RoundType {
        ALL,
        NONE,
        LEFT_TOP
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundCornerImageView);
        //根据属性名称获取对应的值，属性名称的格式为类名_属性名
        rect_adius = typedArray.getFloat(R.styleable.RoundCornerImageView_rect_adius, 100f);
        init();
    }

    public RoundCornerImageView(Context context) {
        super(context);
        init();
    }

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        //
        float density = getResources().getDisplayMetrics().density;
        rect_adius = rect_adius * density;
    }

    public void setRectAdius(float adius) {
        rect_adius = adius;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {

        if (mRoundType == RoundType.ALL) {
            canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
            canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);
            canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
            super.draw(canvas);
            canvas.restore();
            return;
        }

        if (mRoundType == RoundType.LEFT_TOP) {
            canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);

            Path path = new Path();
            float[] radii = {rect_adius, rect_adius, 0f, 0f, 0f, 0f, 0f, 0f};
            path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), radii, Path.Direction.CW);
            canvas.drawPath(path, zonePaint);

            canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
            super.draw(canvas);
            canvas.restore();
            return;
        }

        super.draw(canvas);
    }
}
