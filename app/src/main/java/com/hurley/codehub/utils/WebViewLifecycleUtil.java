package com.hurley.codehub.utils;

import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/20 上午11:22
 *      github : https://github.com/HurleyJames
 *      desc   : WebView生命周期优化工具
 * </pre>
 */
public final class WebViewLifecycleUtil {

    private static final String TAG = "WebViewLifecycleUtil";

    public static void onResume(WebView webView) {
        webView.onResume();
        webView.resumeTimers();
    }

    public static void onPause(WebView webView) {
        webView.onPause();
        webView.pauseTimers();
    }

    public static void onDestroy(WebView webView) {
        ((ViewGroup) webView.getParent()).removeView(webView);
        //清除历史记录
        webView.clearHistory();
        //停止加载
        webView.stopLoading();
        //加载一个空白页
        webView.loadUrl("about:blank");
        webView.setWebChromeClient(null);
        webView.setWebViewClient(null);
        //移除WebView所有的View对象
        webView.removeAllViews();
        //销毁此的WebView的内部状态
        webView.destroy();
    }
}
