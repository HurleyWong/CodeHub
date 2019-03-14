package com.hurley.wanandroid.module.main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.utils.WebViewLifecycleUtils;
import com.zyyoona7.popup.EasyPopup;

import butterknife.BindView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/20 上午11:25
 *      github : https://github.com/HurleyJames
 *      desc   : 浏览器界面
 * </pre>
 */
public class WebActivity extends BaseActivity {

    //TODO 清除缓存

    private static final String TAG = "WebActivity";

    @BindView(R.id.pb_web_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.wv_web_view)
    WebView mWebView;

    /**
     * 当前url
     * @return
     */
    private String mCurrentUrl;

    private EasyPopup mPopup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        //显示滚动条
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(true);

        WebSettings settings = mWebView.getSettings();
        //允许文件访问
        settings.setAllowFileAccess(true);
        //支持JavaScript，默认为false
        settings.setJavaScriptEnabled(true);
        //允许网页定位
        settings.setGeolocationEnabled(true);

        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            //后退网页并且拦截该事件
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        WebViewLifecycleUtils.onResume(mWebView);
        super.onResume();
    }

    @Override
    protected void onPause() {
        WebViewLifecycleUtils.onPause(mWebView);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        WebViewLifecycleUtils.onDestroy(mWebView);
        super.onDestroy();
    }

    /**
     * 点击分享按钮
     * 调用系统自带分享功能
     */
    private void clickShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mCurrentUrl);
        startActivity(Intent.createChooser(intent, getString(R.string.more_share)));
    }

    /**
     * 复制链接
     * @param content
     */
    private void clickCopy(CharSequence content) {
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
     * 用浏览器打开网页
     */
    private void clickExplorer() {
        Uri uri = Uri.parse(mCurrentUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private class MyWebViewClient extends WebViewClient {
        /**
         * 网页加载错误时回调，这个方法会在onPageFinished之前调用
         * @param webView
         * @param request
         * @param error
         */
        @Override
        public void onReceivedError(WebView webView, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(webView, request, error);
        }

        /**
         * 开始加载网页
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(final WebView view, final String url, Bitmap favicon) {
            //显示网页加载界面
            mProgressBar.setVisibility(View.VISIBLE);
            mCurrentUrl = url;
        }

        /**
         * 完成加载网页
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            //隐藏网页加载界面
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //一定要去除这行代码，否则设置无效
            //super.onReceivedSslError(view, handler, error);

            //Android默认的处理方式
            //handler.cancel();

            //接受所有网站的证书
            handler.proceed();

            //进行其它处理
            //handleMessage(Message msg);
        }

        /**
         * 跳转到其它链接
         * @param webView
         * @param request
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String scheme = Uri.parse(request.getUrl().toString()).getScheme();
                if (scheme != null) {
                    scheme = scheme.toLowerCase();
                }
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    mWebView.loadUrl(request.getUrl().toString());
                }
            }
            //已经处理该链接请求
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        /**
         * 收到网页标题
         * @param view
         * @param title
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (title != null) {
                //访问微信公众号文章会收到两次标题名
                setTitle(title);
            }
        }

        /**
         * 收到加载进度变化
         * @param view
         * @param newProgress
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);
        }
    }
}
