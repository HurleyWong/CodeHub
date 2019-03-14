package com.hurley.wanandroid.module.user.register;

import android.widget.Button;
import android.widget.EditText;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.utils.EditTextInputHelper;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/17 上午10:18
 *      github : https://github.com/HurleyJames
 *      desc   : 注册界面
 * </pre>
 */
public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";

    @BindView(R.id.et_register_username)
    EditText mUserName;
    @BindView(R.id.et_register_password)
    EditText mPassword;
    @BindView(R.id.et_register_confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.btn_register_commit)
    Button mRegister;

    private EditTextInputHelper mEditTextInputHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onDestroy() {
        mEditTextInputHelper.removeViews();
        super.onDestroy();
    }
}
