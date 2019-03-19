package com.hurley.wanandroid.module.user.about;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.bean.OpenSourceBean;
import com.hurley.wanandroid.module.adapter.OpenSourceAdapter;
import com.hurley.wanandroid.module.main.WebActivity;
import com.hurley.wanandroid.utils.IntentUtil;
import com.hurley.wanandroid.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/21 下午1:27
 *      github : https://github.com/HurleyJames
 *      desc   : 开源框架界面
 * </pre>
 */
@Route(path = "/about/OpenSourceActivity")
public class OpenSourceActivity extends BaseActivity {

    private static final String TAG = "OpenSourceActivity";

    /**
     * 开源协议适配器
     */
    private OpenSourceAdapter mAdapter;

    private List<OpenSourceBean> mList;


    @BindView(R.id.rv_open_source)
    RecyclerView mRvOpenSource;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open_source;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvOpenSource.setLayoutManager(layoutManager);
        //绘制RecyclerView中item的间距
        mRvOpenSource.addItemDecoration(
                new RecyclerViewDivider(this, LinearLayout.VERTICAL, R.drawable.shape_recyclerview_decoration));
        //创建适配器
        mAdapter = new OpenSourceAdapter(R.layout.item_open_source, mList);
        //给RecyclerView绘制适配器
        mRvOpenSource.setAdapter(mAdapter);

        //item点击事件
        mAdapter.setOnItemClickListener(((adapter, view, position) ->
                //打开对应框架的Github链接
                IntentUtil.getInstance(WebActivity.class)
                        .putString("https://github.com/" + mList.get(position).getAuthor() + "/" + mList.get(position).getName())
                        .startActivity(this)
        ));
    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }
}
