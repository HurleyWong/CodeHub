package com.hurley.wanandroid.module.user.about;

import android.content.Intent;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/24 10:18 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 开发者界面
 * </pre>
 */
@Route(path = PathContainer.AUTHOR)
public class AuthorActivity extends BaseActivity {

    @BindView(R.id.fl_author)
    FrameLayout mFlAuthor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_author;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        AboutBuilder builder = AboutBuilder.with(this)
                //设置开发者照片，从Github Api获取
                //设置加载动画
                .setLinksAnimated(true)
                .setName("Hurley")
                .setSubTitle("Senior Student")
                .setBrief("Having dreams is what makes life tolerable.")
                .setLinksColumnsCount(4)
                .addGitHubLink("hurleyjames")
                .addEmailLink("1401682479@qq.com")
                .addPrivacyPolicyAction("")
                .addHelpAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        mFlAuthor.addView(view);
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
