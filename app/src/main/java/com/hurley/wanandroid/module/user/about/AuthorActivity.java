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
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Hurley")
                .setSubTitle("Senior Student")
                //Links分为4列
                .setLinksColumnsCount(4)
                .setBrief("Having dreams is what makes life tolerable.")
                .addAndroidLink("user")
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("hurleyjames")
                .addWebsiteLink("http://www.hurley.fun")
                .addFacebookLink("100014949587803")
                .addTwitterLink("HurleyHuang23")
                .addInstagramLink("hurleyhuang")
                .addDribbbleLink("user")
                .addSkypeLink("user")
                .addGoogleLink("user")
                .addLinkedInLink("arleu-cezar-vansuita-júnior-83769271")
                .addEmailLink("1401682479@qq.com")
                .addFiveStarsAction()
                .addMoreFromMeAction("Vansuita")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                //Action分为3列
                .setActionsColumnsCount(3)
                .addFeedbackAction("1401682479@qq.com")
                .addPrivacyPolicyAction("http://www.docracy.com/2d0kis6uc2")
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
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
