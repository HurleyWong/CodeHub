package com.hurley.wanandroid.module.user.about;

import android.content.pm.PackageManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.bean.OpenSourceBean;
import com.hurley.wanandroid.module.adapter.OpenSourceAdapter;
import com.hurley.wanandroid.module.web.WebActivity;
import com.hurley.wanandroid.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/17 下午12:26
 *      github : https://github.com/HurleyJames
 *      desc   : 关于界面
 * </pre>
 */
@Route(path = PathContainer.ABOUT)
public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";

    @BindView(R.id.about_version_text)
    TextView mTvAboutVersion;
    @BindView(R.id.about_author)
    LinearLayout mLlAboutAuthor;
    @BindView(R.id.about_wan_android)
    LinearLayout mLlAboutWanAndroid;
    @BindView(R.id.rv_open_source)
    RecyclerView mRvOpenSource;

    /**
     * 开源协议适配器
     */
    private OpenSourceAdapter mAdapter;

    private List<OpenSourceBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        showAboutContent();

        mList = new ArrayList<>();

        //创建布局管理
        mRvOpenSource.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器
        mAdapter = new OpenSourceAdapter(R.layout.item_open_source, mList);
        //给RecyclerView绘制适配器
        mRvOpenSource.setAdapter(mAdapter);
        mAdapter.setNewData(getListData());

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

    @OnClick({R.id.about_author, R.id.about_wan_android})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_author:
                ARouter.getInstance().build(PathContainer.AUTHOR).navigation();
                break;
            case R.id.about_wan_android:
                //打开玩Android网页
                WebActivity.startWeb(getString(R.string.wan_android_address));
                break;
            default:
                break;
        }
    }

    /**
     * 显示关于界面内容
     */
    private void showAboutContent() {
        try {
            String versionStr = getString(R.string.app_name)
                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mTvAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<OpenSourceBean> getListData() {
        List<OpenSourceBean> list = new ArrayList<>();
        list.add(new OpenSourceBean(getString(R.string.license_ButterKnife), getString(R.string.author_ButterKnife), getString(R.string.detail_ButterKnife)));
        list.add(new OpenSourceBean(getString(R.string.license_Retrofit), getString(R.string.author_Retrofit), getString(R.string.detail_Retrofit)));
        return list;
    }

}
