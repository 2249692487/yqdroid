package com.ywqln.yqdroid.ui.adapter;

import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.entity.resp.model.CommentModel;

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

    public MessageAdapter(List<CommentModel> dataSource) {
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

        CommentModel comment = dataSource.get(position);

        TextView tvUser = ViewHolder.get(convertView, R.id.tv_user);
        TextView tvInfoContent = ViewHolder.get(convertView, R.id.tv_infoContent);
        TextView tvInfoTime = ViewHolder.get(convertView, R.id.tv_infoTime);
        LinearLayout llComment = ViewHolder.get(convertView, R.id.ll_comment);

        tvUser.setText(comment.getNickname());
        tvInfoContent.setText(comment.getContent());
        tvInfoTime.setText(mill2date(comment.getTime()));

        llComment.removeAllViews();
        llComment.setVisibility(View.GONE);
        List<CommentModel> commentList = comment.getComment_son();
        if (comment.getComment_son().size() > 0) {
            llComment.setVisibility(View.VISIBLE);
            addCommentLayout(parent, convertView, llComment, commentList);
        }

        return convertView;
    }

    private void addCommentLayout(ViewGroup parent, View convertView, LinearLayout ll_comment,
            List<CommentModel> commentList) {

        for (CommentModel item : commentList) {
            View commentLayout = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_comment, null);

//            TextView tvMasterUser = ViewHolder.get(convertView, R.id.tv_masterUser);
//            TextView tvReplayUser = ViewHolder.get(convertView, R.id.tv_replayUser);
//            TextView tvContent = ViewHolder.get(convertView, R.id.tv_content);
//            TextView tvTime = ViewHolder.get(convertView, R.id.tv_time);
//
//            tvMasterUser.setText(item.getNickname());
//            tvReplayUser.setText(item.getoNickname());
//            tvContent.setText(item.getContent());
//            tvTime.setText(mill2date(item.getTime()));

            ll_comment.addView(commentLayout);
        }

        AppCompatButton button = new AppCompatButton(parent.getContext());
        button.setText("展开");
        ll_comment.addView(button);
    }

    private String mill2date(String mill) {
        if (TextUtils.isEmpty(mill)) {
            return "";
        }
        long time = Long.parseLong(mill);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(new Date(time));
        return result;
    }
}
