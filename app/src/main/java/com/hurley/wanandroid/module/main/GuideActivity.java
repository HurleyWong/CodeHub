package com.hurley.wanandroid.module.main;

import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.base.BaseActivity;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/18 5:44 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 首次打开应用的引导页面
 * </pre>
 */
public class GuideActivity extends BaseActivity {

    private static final String TAG = "GuideActivity";

    @BindView(R.id.banner_guide)
    BGABanner mBannerGuide;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {
        setBannerListener();
        processLogic();
    }

    /**
     * 设置Banner监听器
     */
    private void setBannerListener() {
        mBannerGuide.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, () -> {
            ARouter.getInstance().build("/main/HomeActivity").navigation();
            finish();
        });
    }

    /**
     * 加载逻辑
     */
    private void processLogic() {
        //Bitmap的宽高在 maxWidth maxHeight 和 minWidth minHeight之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        //设置数据源
        mBannerGuide.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.mipmap.building,
                R.mipmap.sky,
                R.mipmap.night);
    }












}
