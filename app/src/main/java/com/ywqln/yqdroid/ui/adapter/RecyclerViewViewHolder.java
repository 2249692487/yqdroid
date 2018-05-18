package com.ywqln.yqdroid.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ywqln.yqdroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述:RecyclerView的ViewHolder示例代码.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/5/17
 */
public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_textView)
    TextView itemTextView;
    @BindView(R.id.profilePhoto)
    ImageView profilePhoto;
    @BindView(R.id.heroDetail)
    TextView heroDetail;

    public TextView getItemTextView() {
        return itemTextView;
    }

    public ImageView getProfilePhoto() {
        return profilePhoto;
    }

    public TextView getHeroDetail() {
        return heroDetail;
    }

    public RecyclerViewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
