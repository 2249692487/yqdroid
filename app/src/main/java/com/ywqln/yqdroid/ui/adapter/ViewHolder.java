package com.ywqln.yqdroid.ui.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:Adapter布局返回View
 */
public class ViewHolder {
    private ViewHolder() {
    }

    // I added a generic return type to reduce the casting noise in client code
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
