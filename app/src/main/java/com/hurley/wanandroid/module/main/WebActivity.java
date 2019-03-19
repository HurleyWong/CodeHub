package com.hurley.wanandroid.module.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/20 上午11:25
 *      github : https://github.com/HurleyJames
 *      desc   : 浏览器界面
 * </pre>
 */
@Route(path = PathContainer.WEB)
public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    @Autowired
    public int id;
    @Autowired
    public String url;
    @Autowired
    public String title;
    @Autowired
    public String author;

    @BindView(R.id.web_content)
    FrameLayout mFlWebContent;

    AgentWeb mAgentWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        //传入Activity
        mAgentWeb = AgentWeb.with(this)
                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .setAgentWebParent(mFlWebContent, new LinearLayout.LayoutParams(-1, -1))
                //使用默认进度条
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //打开其他应用时，弹窗咨询用户是否前往其他应用
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
        setWebDisplay();
    }

    /**
     * 显示返回键
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_refresh:
                //刷新
                mAgentWeb.getWebCreator().getWebView().loadUrl(url);
                break;
            case R.id.more_share:
                //分享
                shareWeb();
                break;
            case R.id.more_collect:
                //收藏
                break;
            case R.id.more_copy_links:
                //复制链接
                copyLink(url);
                //提示复制链接成功
                toast(R.string.more_copy_links_success);
                break;
            case R.id.more_open_by_browser:
                //用浏览器打开
                openByBrowser();
                break;
            case R.id.more_clear_cache:
                //TODO 显示缓存占用多少
                //清理缓存
                AgentWebConfig.clearDiskCache(this);
                //提示清除缓存成功
                toast(R.string.more_clear_cache_success);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 返回上一页而不是finish Activity
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //设置标题
            setToolbarTitle(title);
        }
    };

    /**
     * 设置网页显示效果
     */
    private void setWebDisplay() {
        WebSettings settings = mAgentWeb.getWebCreator().getWebView().getSettings();

        //是否支持ViewPoint属性，默认值false
        //自适应手机屏幕
        settings.setUseWideViewPort(true);
        //布局算法
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //是否使用overview mode加载页面，默认值为false
        //当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度
        settings.setLoadWithOverviewMode(true);

        //缩放
        settings.setSupportZoom(true);
        //是否使用内置缩放机制
        settings.setBuiltInZoomControls(true);
        //是否显示内置缩放控件
        settings.setDisplayZoomControls(false);

        //支持播放gif动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //解决Android5.0上WebView默认不允许加载Http与Https混合内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两者都可以
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //加快HTML网页加载完成的速度，等页面finish后再加载图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
    }


    /**
     * 点击文章打开网页
     * @param id            文章id
     * @param url           文章链接
     * @param title         文章标题
     * @param author        文章作者
     */
    public static void startWeb(int id, String url, String title, String author) {
        ARouter.getInstance().build(PathContainer.WEB)
                .withInt(Constants.CONTENT_ID_KEY, id)
                .withString(Constants.CONTENT_URL_KEY, url)
                .withString(Constants.CONTENT_TITLE_KEY, title)
                .withString(Constants.CONTENT_AUTHOR_KEY, author)
                .navigation();
    }

    /**
     * 直接打开网页
     * @param url           网页链接
     */
    public static void startWeb(String url) {
        ARouter.getInstance().build(PathContainer.WEB)
                .withString(Constants.CONTENT_URL_KEY, url)
                .navigation();
    }

    /**
     * 分享
     * 调用系统自带分享功能
     */
    private void shareWeb() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(intent, getString(R.string.more_share)));
    }

    /**
     * 复制链接
     * @param content
     */
    private void copyLink(CharSequence content) {
        ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            //参数一：标签，可为空，参数二：要复制到剪贴板的文本
            clipboard.setPrimaryClip(ClipData.newPlainText(null, content));
            if (clipboard.hasPrimaryClip()) {
                clipboard.getPrimaryClip().getItemAt(0).getText();
            }
        }
    }

    /**
     * 用浏览器打开
     */
    private void openByBrowser() {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
