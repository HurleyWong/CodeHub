package com.hurley.wanandroid.module.user.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.event.LoginEvent;
import com.hurley.wanandroid.net.callback.RxBus;
import com.hurley.wanandroid.utils.EditTextInputHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午3:52
 *      github : https://github.com/HurleyJames
 *      desc   : 登录界面
 * </pre>
 */
@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_login_username)
    EditText mEtUsername;
    @BindView(R.id.et_login_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login_commit)
    Button mBtnCommit;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_login_forget)
    TextView mTvForget;

    private EditTextInputHelper mEditTextInputHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        setToolbarTitle("");
        mEtUsername.setText(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.USERNAME));
        mEtPassword.setText(SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getString(Constants.PASSWORD));
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.LOGIN_STATUS, true);
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.USERNAME, userBean.getUsername());
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.PASSWORD, userBean.getPassword());
        //登录成功后通知其他界面
        RxBus.getInstance().post(new LoginEvent());
        this.finish();
    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({R.id.btn_login_commit, R.id.tv_register, R.id.tv_login_forget})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_commit:
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                    //如果用户名或密码为空
                    toast(R.string.login_username_password_null);
                    return;
                }
                mPresenter.login(username, password);
                break;
            case R.id.tv_register:
                //跳转至注册界面
                ARouter.getInstance().build("/register/RegisterActivity").navigation();
                break;
            case R.id.tv_login_forget:
                break;
            default:
                break;
        }
    }
}
