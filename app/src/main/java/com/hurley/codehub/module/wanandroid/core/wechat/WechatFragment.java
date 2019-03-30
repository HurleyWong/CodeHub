package com.hurley.codehub.module.wanandroid.core.wechat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.hurley.codehub.R;
import com.hurley.codehub.base.BaseFragment;
import com.hurley.codehub.bean.wanandroid.WxAccountBean;
import com.hurley.codehub.module.wanandroid.core.adapter.WechatAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午7:06
 *      github : https://github.com/HurleyJames
 *      desc   : 公众号界面
 * </pre>
 */
public class WechatFragment extends BaseFragment<WechatPresenter> implements WechatContract.View {

    private static final String TAG = "WechatFragment";

    @Autowired
    public List<WxAccountBean> mWxAccounts;

    @BindView(R.id.tl_wechat)
    TabLayout mTlWechat;
    @BindView(R.id.srl_wechat)
    SwipeRefreshLayout mSrlWechat;
    @BindView(R.id.vp_wechat)
    ViewPager mVpWechat;


    private WechatAdapter mWechatAdapter;

    public static WechatFragment newInstance() {
        Bundle args = new Bundle();
        WechatFragment fragment = new WechatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        mPresenter.loadWxAccounts();
    }

    @Override
    public void setWxAccounts(List<WxAccountBean> wxAccounts) {
        List<Integer> ids = new ArrayList<>();
        List<String> names= new ArrayList<>();
        if (wxAccounts.size() > 0) {
            for (WxAccountBean wxAccountBean : wxAccounts) {
                ids.add(wxAccountBean.getId());
                names.add(wxAccountBean.getName());
            }
        }

        mWechatAdapter = new WechatAdapter(getChildFragmentManager(), names, ids);
        mVpWechat.setAdapter(mWechatAdapter);
        mVpWechat.setOffscreenPageLimit(1);
        mVpWechat.setCurrentItem(0, false);
        //将TabLayout与ViewPager绑定
        mTlWechat.setupWithViewPager(mVpWechat, true);

        /*mFragments.clear();
        for (WxAccountBean wxAccountBean : wxAccounts) {
            mFragments.add(SystemArticleListFragment.getInstance(wxAccountBean.getId(), wxAccountBean.getName()));
        }
        initTabLayoutAndViewPager(wxAccounts);*/
    }
}
