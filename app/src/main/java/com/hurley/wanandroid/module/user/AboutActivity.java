package com.hurley.wanandroid.module.user;

import android.content.pm.PackageManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/17 下午12:26
 *      github : https://github.com/HurleyJames
 *      desc   : 关于界面
 * </pre>
 */
public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";

    @BindView(R.id.about_version_text)
    TextView mTvAboutVersion;
    @BindView(R.id.about_wan_android)
    LinearLayout mLlAboutWanAndroid;
    @BindView(R.id.about_open_source)
    LinearLayout mLlAboutOpenSource;
    @BindView(R.id.about_github)
    LinearLayout mLlAboutGithub;
    @BindView(R.id.about_weibo)
    LinearLayout mLlAboutWeibo;

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
