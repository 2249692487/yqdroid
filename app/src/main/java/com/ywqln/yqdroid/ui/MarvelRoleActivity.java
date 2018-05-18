package com.ywqln.yqdroid.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ywqln.yqdroid.R;
import com.ywqln.yqdroid.base.BaseActivity;
import com.ywqln.yqdroid.presenter.MarvelRolePresenter;
import com.ywqln.yqdroid.ui.adapter.RecyclerViewAdapter;

import butterknife.BindView;

/**
 * 描述:RecyclerView示例代码.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2018/5/17
 */
public class MarvelRoleActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;

    @Override
    protected int layoutResId() {
        return R.layout.activity_marvel_role;
    }

    @Override
    protected void initViews() {
//        String postParams = "loc=aaa&name=zhangsan&hero=钢铁侠";
//        webView.postUrl("http://10.5.235.77:13937/Index.aspx", postParams.getBytes());
    }

    @Override
    protected void initComplete() {
        adapter = new RecyclerViewAdapter();
        adapter.setDataSource(new MarvelRolePresenter().getHeroList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }
}
