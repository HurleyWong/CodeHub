package com.hurley.wanandroid.module.wechat;

import android.view.View;

import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;


/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午7:06
 *      github : https://github.com/HurleyJames
 *      desc   : 公众号界面
 * </pre>
 */
public class WechatFragment extends BaseFragment<WechatPresenter> implements WechatContract.View {

    public static WechatFragment newInstance() {
        return new WechatFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

}
