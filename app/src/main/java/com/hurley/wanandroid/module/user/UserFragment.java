package com.hurley.wanandroid.module.user;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseFragment;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午7:20
 *      github : https://github.com/HurleyJames
 *      desc   : 用户界面
 * </pre>
 */
public class UserFragment extends BaseFragment<UserPresenter> implements UserContract.View {

    private static final String TAG = "UserFragment";

    @BindView(R.id.ll_login)
    LinearLayout mLlLogin;
    @BindView(R.id.btn_user_collect)
    Button mBtnCollect;
    @BindView(R.id.btn_user_setting)
    Button mBtnSetting;
    @BindView(R.id.btn_user_about)
    Button mBtnAbout;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }


}
