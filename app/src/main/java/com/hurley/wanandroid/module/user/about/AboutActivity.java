package com.hurley.wanandroid.module.user.about;

import android.content.pm.PackageManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.module.web.WebActivity;

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
    @BindView(R.id.about_content)
    LinearLayout mLlAboutContent;
    @BindView(R.id.about_wan_android)
    LinearLayout mLlAboutWanAndroid;
    @BindView(R.id.about_open_source)
    LinearLayout mLlAboutOpenSource;
    @BindView(R.id.about_github)
    LinearLayout mLlAboutGithub;

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
    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @OnClick({R.id.about_content, R.id.about_wan_android, R.id.about_open_source, R.id.about_github})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_content:
                ARouter.getInstance().build(PathContainer.AUTHOR).navigation();
                break;
            case R.id.about_wan_android:
                //打开玩Android网页
                WebActivity.startWeb(getString(R.string.wan_android_address));
                break;
            case R.id.about_open_source:
                //跳转至开源框架界面
                ARouter.getInstance().build(PathContainer.OPEN_SOURCE).navigation();
                break;
            case R.id.about_github:
                //打开Github主页
                WebActivity.startWeb(getString(R.string.github_address));
                break;
            default:
                break;
        }
    }

    /**
     * 显示关于界面内容
     */
    private void showAboutContent() {
        /*mTvAboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        mTvAboutContent.setMovementMethod(LinkMovementMethod.getInstance());*/
        try {
            String versionStr = getString(R.string.app_name)
                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mTvAboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
