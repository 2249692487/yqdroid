package com.ywqln.yqdroid.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.entity.resp.HerosDo;
import com.ywqln.yqdroid.util.GlideProvider;

import java.util.List;

/**
 * 描述:RecyclerView的Adapter示例代码.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/5/17
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private DrawableRequestBuilder drawableRequestBuilder;
    private List<HerosDo> dataSource;

    public List<HerosDo> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<HerosDo> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).getDecent();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HerosDo.DECENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_decent,
                    parent, false);
            RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);
            return viewHolder;
        }

        if (drawableRequestBuilder == null) {
            drawableRequestBuilder = Glide.with(parent.getContext())
                    .fromString()
                    .fitCenter()
                    .crossFade();
        }

        if (viewType == HerosDo.VILLAIN) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_villain,
                    parent, false);
            RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);
            return viewHolder;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_decent,
                parent, false);
        RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        HerosDo hero = dataSource.get(position);
        RecyclerViewViewHolder roleHolder = null;
        switch (viewType) {
            case HerosDo.DECENT:
                roleHolder = (RecyclerViewViewHolder) holder;
                roleHolder.getItemTextView().setText(hero.getName());
                break;
            case HerosDo.VILLAIN:
                roleHolder = (RecyclerViewViewHolder) holder;
                roleHolder.getItemTextView().setText(hero.getName());
                break;
            default:
                Log.d("RecyclerviewAdapter", "onBindViewHolder switch default");
                break;
        }

        if (drawableRequestBuilder == null) {
            drawableRequestBuilder = Glide.with(roleHolder.getProfilePhoto().getContext())
                    .fromString()
                    .fitCenter()
                    .crossFade();
        }

        GlideProvider.loadWithWifi(drawableRequestBuilder, roleHolder.getProfilePhoto(),
                hero.getProfilePhoto(),
                R.mipmap.app_icon, R.mipmap.app_icon);
        roleHolder.getItemTextView().setText(hero.getName());
        roleHolder.getHeroDetail().setText(hero.getPower());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
