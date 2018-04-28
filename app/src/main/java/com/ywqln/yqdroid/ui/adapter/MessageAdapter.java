package com.ywqln.yqdroid.ui.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;
import com.ywqln.yqdroid.util.GlideProvider;
import com.ywqln.yqdroid.widgets.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yanwenqiang on 2018/4/23.
 * <p>
 * 描述:待描述
 */
public class MessageAdapter extends BaseAdapter {
    private List<CommentModel> dataSource;
    DrawableRequestBuilder drawableRequestBuilder;
    private CommentItemClickListener commentClickListener;

    public MessageAdapter(List<CommentModel> dataSource,
            CommentItemClickListener commentClickListener) {
        this.dataSource = dataSource;
        this.commentClickListener = commentClickListener;
    }

    public List<CommentModel> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<CommentModel> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,
                    null);
        }
        if (drawableRequestBuilder == null) {
            drawableRequestBuilder = Glide.with(parent.getContext())
                    .fromString()
                    .centerCrop()
                    .crossFade();
        }

        CommentModel comment = dataSource.get(position);

        TextView tvUser = ViewHolder.get(convertView, R.id.tv_user);
        TextView tvInfoContent = ViewHolder.get(convertView, R.id.tv_infoContent);
        TextView tvInfoTime = ViewHolder.get(convertView, R.id.tv_infoTime);
        CircleImageView imgMasterHeader = ViewHolder.get(convertView, R.id.img_master_header);
        LinearLayout llComment = ViewHolder.get(convertView, R.id.ll_comment);
        LinearLayout llContent = ViewHolder.get(convertView, R.id.ll_content);

        llContent.setTag(new int[]{position});
        llContent.setOnClickListener(view -> {
            if (commentClickListener != null) {
                commentClickListener.commentClick(view, CommentLevel.ROOT);
            }
        });
        tvUser.setText(comment.getNickname());
        tvInfoContent.setText(comment.getContent());
        tvInfoTime.setText(mill2date(comment.getTime()));
        GlideProvider.loadWithWifi(drawableRequestBuilder, imgMasterHeader, comment.getAvatar(),
                R.mipmap.app_icon, R.mipmap.app_icon);

        llComment.removeAllViews();
        llComment.setVisibility(View.GONE);
        List<CommentModel> commentList = comment.getComment_son();
        if (comment.getComment_son().size() > 0) {
            llComment.setVisibility(View.VISIBLE);
            addCommentLayout(parent, position, llComment, commentList);
        }

        return convertView;
    }

    private void addCommentLayout(ViewGroup parent, int position, LinearLayout ll_comment,
            List<CommentModel> commentList) {

        boolean isClosed = dataSource.get(position).isColsed();

        for (int i = 0; i < commentList.size(); i++) {
            View commentLayout = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_comment, null);

            CommentModel comment = commentList.get(i);
            int[] indexs = new int[]{position, i};
            commentLayout.setTag(indexs);
            commentLayout.setOnClickListener((View view) -> {
                if (commentClickListener != null) {
                    commentClickListener.commentClick(view, CommentLevel.CHILD);
                }
            });
            TextView tvMasterUser = commentLayout.findViewById(R.id.tv_masterUser);
            TextView tvReplayUser = commentLayout.findViewById(R.id.tv_replayUser);
            TextView tvContent = commentLayout.findViewById(R.id.tv_content);
            TextView tvTime = commentLayout.findViewById(R.id.tv_time);
            CircleImageView imgSubHeader = commentLayout.findViewById(R.id.img_sub_header);

            tvMasterUser.setText(comment.getNickname());
            tvReplayUser.setText(comment.getoNickname());
            tvContent.setText(comment.getContent());
            tvTime.setText(mill2date(comment.getTime()));
            GlideProvider.loadWithWifi(drawableRequestBuilder, imgSubHeader, comment.getAvatar(),
                    R.mipmap.app_icon, R.mipmap.app_icon);

            ll_comment.addView(commentLayout);
            if (isClosed) {
                if (i == 2) {
                    addExpandHandler(parent, position, "展开", ll_comment);
                    break;
                }
            } else {
                if (i == commentList.size() - 1) {
                    addExpandHandler(parent, position, "收起", ll_comment);
                }
            }
        }
    }

    private void addExpandHandler(ViewGroup parent, int position, String defaultTxt,
            LinearLayout ll_comment) {
        View expandLayout = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_expand_close, null);

        AppCompatTextView expandCloseText = expandLayout.findViewById(R.id.atv_expand_close);
        expandCloseText.setText(defaultTxt);
        expandCloseText.setTag(position);
        ll_comment.addView(expandLayout);
        expandCloseText.setOnClickListener(view -> {
            int index = (int) view.getTag();
            boolean state = dataSource.get(index).isColsed();
            dataSource.get(index).setColsed(!state);
            notifyDataSetChanged();
            AppCompatTextView textView = (AppCompatTextView) view;
            if (state) {
                textView.setText("展开");
            } else {
                textView.setText("收起");
            }
        });
    }

    private String mill2date(String mill) {
        if (TextUtils.isEmpty(mill)) {
            return "";
        }
        long time = Long.parseLong(mill);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(new Date(time));
        return result;
    }


    public enum CommentLevel {
        ROOT,
        CHILD
    }

    public interface CommentItemClickListener {
        void commentClick(View view, CommentLevel level);
    }
}
