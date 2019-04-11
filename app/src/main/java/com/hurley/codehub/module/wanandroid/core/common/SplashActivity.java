package com.hurley.codehub.module.wanandroid.core.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.module.wanandroid.core.main.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/19 11:26 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 闪屏页面
 * </pre>
 */
@Route(path = PathContainer.SPLASH)
public class SplashActivity extends AppCompatActivity implements OnPermission, Animation.AnimationListener {

    @BindView(R.id.iv_splash_bg)
    ImageView mIvSplashBg;
    @BindView(R.id.iv_splash_icon)
    ImageView mIvSplashIcon;
    @BindView(R.id.tv_splash_name)
    TextView mTvSplashName;

    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //判断是否是第一次打开应用
        mSharedPreference = getSharedPreferences("GuideActivity", MODE_PRIVATE);
        if (mSharedPreference.getBoolean("first_open", true)) {
            mEditor = mSharedPreference.edit();
            mEditor.putBoolean("first_open", false);
            mEditor.apply();
            Intent intent = new Intent();
            intent.setClass(this, GuideActivity.class);
            this.startActivity(intent);
            finish();
        }

        /*boolean isFirstOpen = SharedPreferencesUtil.getBoolean(this,
                SharedPreferencesUtil.FIRST_OPEN, true);
        if (isFirstOpen) {
            //如果是首次打开应用，则显示引导页
            ARouter.getInstance().build("/main/GuideActivity");
            finish();
            return;
        }*/

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initStartAnim();
        ImmersionBar.with(this)
                .fullScreen(true)
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                .transparentNavigationBar()
                .init();
    }

    private void requestFilePermission() {
        XXPermissions.with(this)
                .permission(Permission.Group.STORAGE)
                .request(this);
    }

    @Override
    public void hasPermission(List<String> granted, boolean isAll) {
        Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);
        this.startActivity(intent);

        //使用ARouter会加载变慢，出现一个短暂的白屏效果
        //ARouter.getInstance().build("/main/HomeActivity").navigation();
        finish();
    }

    @Override
    public void noPermission(List<String> denied, boolean quick) {
        if (quick) {
            ToastUtils.showShort(R.string.no_file_permission);
            XXPermissions.gotoPermissionSettings(SplashActivity.this, true);
        }else {
            ToastUtils.showShort(R.string.grant_file_permission);
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestFilePermission();
                }
            }, Constants.SPLASH_TIME);
        }
    }

    /**
     * 启动动画
     */
    private void initStartAnim() {
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
        aa.setDuration(Constants.ANIM_TIME * 2);
        aa.setAnimationListener(this);
        mIvSplashBg.startAnimation(aa);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(Constants.ANIM_TIME);
        mIvSplashIcon.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(Constants.ANIM_TIME);
        mTvSplashName.startAnimation(ra);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        requestFilePermission();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (XXPermissions.isHasPermission(SplashActivity.this, Permission.Group.STORAGE)) {
            hasPermission(null, true);
        } else {
            requestFilePermission();
        }
    }
}
