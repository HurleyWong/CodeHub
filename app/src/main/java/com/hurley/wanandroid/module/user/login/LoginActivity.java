package com.hurley.wanandroid.module.user.login;

import android.widget.Button;
import android.widget.EditText;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.utils.EditTextInputHelper;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午3:52
 *      github : https://github.com/HurleyJames
 *      desc   : 登录界面
 * </pre>
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_login_username)
    EditText mUserNameView;
    @BindView(R.id.et_login_password)
    EditText mPasswordView;
    @BindView(R.id.btn_login_commit)
    Button mCommitView;

    private EditTextInputHelper mEditTextInputHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void loginSuccess(UserBean userBean) {

    }
}
