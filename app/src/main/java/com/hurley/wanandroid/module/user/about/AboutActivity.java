package com.hurley.wanandroid.module.user.about;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.bean.OpenSourceBean;
import com.hurley.wanandroid.module.adapter.OpenSourceAdapter;
import com.hurley.wanandroid.module.web.WebActivity;
import com.hurley.wanandroid.utils.IntentUtil;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.rv_open_source)
    RecyclerView mRvOpenSource;

    /**
     * 开源协议适配器
     */
    private OpenSourceAdapter mAdapter;

    private List<OpenSourceBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        AboutBuilder builder = AboutBuilder.with(this)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                //设置开发者照片，从Github Api获取
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                //设置加载动画
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

        TextView textView = new TextView(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 0, 10);
        textView.setLayoutParams(layoutParams);
        textView.setText(getString(R.string.about_open_source));
        textView.setTextSize(16);
        textView.setTextColor(ContextCompat.getColor(this, R.color.text));


        mList = new ArrayList<>();

        //创建布局管理
        mRvOpenSource.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器
        mAdapter = new OpenSourceAdapter(R.layout.item_open_source, mList);
        //给RecyclerView绘制适配器
        mRvOpenSource.setAdapter(mAdapter);
        mAdapter.setNewData(getListData());
        mAdapter.addHeaderView(view);
        mAdapter.addHeaderView(textView);

        /*//item点击事件
        mAdapter.setOnItemClickListener(((adapter, view, position) ->
                //打开对应框架的Github链接
                IntentUtil.getInstance(WebActivity.class)
                        .putString("https://github.com/" + mList.get(position).getAuthor() + "/" + mList.get(position).getName())
                        .startActivity(this)
        ));*/

    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    /**
     * 添加数据
     * @return
     */
    private List<OpenSourceBean> getListData() {
        List<OpenSourceBean> list = new ArrayList<>();
        list.add(new OpenSourceBean(getString(R.string.license_ButterKnife), getString(R.string.author_ButterKnife), getString(R.string.detail_ButterKnife)));
        list.add(new OpenSourceBean(getString(R.string.license_dagger), getString(R.string.author_dagger), getString(R.string.detail_dagger)));
        list.add(new OpenSourceBean(getString(R.string.license_OkHttp), getString(R.string.author_OkHttp), getString(R.string.detail_OkHttp)));
        list.add(new OpenSourceBean(getString(R.string.license_Retrofit), getString(R.string.author_Retrofit), getString(R.string.detail_Retrofit)));
        list.add(new OpenSourceBean(getString(R.string.license_RxJava), getString(R.string.author_RxJava), getString(R.string.detail_RxJava)));
        list.add(new OpenSourceBean(getString(R.string.license_RxAndroid), getString(R.string.author_RxAndroid), getString(R.string.detail_RxAndroid)));
        list.add(new OpenSourceBean(getString(R.string.license_RxBinding), getString(R.string.author_RxBinding), getString(R.string.detail_RxBinding)));
        list.add(new OpenSourceBean(getString(R.string.license_AgentWeb), getString(R.string.author_AgentWeb), getString(R.string.detail_AgentWeb)));
        list.add(new OpenSourceBean(getString(R.string.license_ARouter), getString(R.string.author_ARouter), getString(R.string.detail_ARouter)));
        list.add(new OpenSourceBean(getString(R.string.license_Glide), getString(R.string.author_Glide), getString(R.string.detail_Glide)));
        list.add(new OpenSourceBean(getString(R.string.license_BaseRecyclerViewAdapterHelper), getString(R.string.author_BaseRecyclerViewAdapterHelperr), getString(R.string.detail_BaseRecyclerViewAdapterHelper)));
        list.add(new OpenSourceBean(getString(R.string.license_Fragmentation), getString(R.string.author_Fragmentation), getString(R.string.detail_Fragmentation)));
        list.add(new OpenSourceBean(getString(R.string.license_FlowLayout), getString(R.string.author_FlowLayout), getString(R.string.detail_FlowLayout)));
        list.add(new OpenSourceBean(getString(R.string.license_Banner), getString(R.string.author_Banner), getString(R.string.detail_Banner)));
        list.add(new OpenSourceBean(getString(R.string.license_OXChart), getString(R.string.author_OXChart), getString(R.string.detail_OXChart)));
        list.add(new OpenSourceBean(getString(R.string.license_MaterialAbout), getString(R.string.author_MaterialAbout), getString(R.string.detail_MaterialAbout)));
        list.add(new OpenSourceBean(getString(R.string.license_ImmersionBar), getString(R.string.author_ImmersionBar), getString(R.string.detail_ImmersionBar)));
        list.add(new OpenSourceBean(getString(R.string.license_XXPermissions), getString(R.string.author_XXPermissions), getString(R.string.detail_XXPermissions)));
        list.add(new OpenSourceBean(getString(R.string.license_AndroidUtilCode), getString(R.string.author_AndroidUtilCode), getString(R.string.detail_AndroidUtilCode)));

        return list;
    }

}
