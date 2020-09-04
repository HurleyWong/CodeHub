package com.hurley.codehub.module.wanandroid.core.user.setting;

import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.LogUtils;
import com.hjq.language.LanguagesManager;
import com.hurley.codehub.R;
import com.hurley.codehub.api.PathContainer;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseActivity;
import com.hurley.codehub.module.wanandroid.event.NightModeEvent;
import com.hurley.codehub.net.callback.RxBus;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.tencent.bugly.beta.Beta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/18 下午2:19
 *      github : https://github.com/HurleyJames
 *      desc   : 设置界面
 * </pre>
 */
@Route(path = PathContainer.SETTING)
public class SettingActivity extends BaseActivity<SettingPresenter>
        implements SettingContract.View, CompoundButton.OnCheckedChangeListener {

    //TODO 左侧小图标

    private static final String TAG = "SettingActivity";

    @BindView(R.id.setting_normal_card)
    CardView mCvNormal;
    @BindView(R.id.setting_other_card)
    CardView mCvOther;
    @BindView(R.id.setting_auto_cache)
    LinearLayout mLlAutoCache;
    @BindView(R.id.cb_setting_auto_cache)
    AppCompatCheckBox mCbAutoCache;
    @BindView(R.id.setting_no_image)
    LinearLayout mLlNoImage;
    @BindView(R.id.cb_setting_no_image)
    AppCompatCheckBox mCbNoImage;
    @BindView(R.id.setting_night)
    LinearLayout mLlNight;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbNight;
    @BindView(R.id.setting_theme_color)
    LinearLayout mLlThemeColor;
    @BindView(R.id.setting_language)
    LinearLayout mLlLanguage;
    @BindView(R.id.setting_language_type)
    TextView mTvLanguageType;
    @BindView(R.id.setting_update)
    LinearLayout mLlUpdate;
    @BindView(R.id.setting_clear_cache)
    LinearLayout mLlClearCache;
    @BindView(R.id.setting_cache_num)
    TextView mTvCacheNum;
    @BindView(R.id.setting_feedback)
    LinearLayout mLlFeedback;

    private File cacheFile;

    boolean restart;

    String cache = CacheUtils.getInstance().getCacheSize() + "kb";

    /**
     * 语言种类
     */
    List<String> languageList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.setting_activity;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        cacheFile = new File(Constants.PATH_CACHE);
        mTvCacheNum.setText(cache);
        LogUtils.e("缓存大小：" + cache);
        mCbAutoCache.setChecked(mPresenter.getAutoCacheState());
        mCbNoImage.setChecked(mPresenter.getNoImageState());
        mCbNight.setChecked(mPresenter.getNightModeState());
        //设置监听事件
        mCbAutoCache.setOnCheckedChangeListener(this);
        mCbNoImage.setOnCheckedChangeListener(this);
        mCbNight.setOnCheckedChangeListener(this);

        languageList.add(getString(R.string.language_auto));
        languageList.add(getString(R.string.language_simplified));
        languageList.add(getString(R.string.language_traditional));
        languageList.add(getString(R.string.language_english));

        Locale locale = LanguagesManager.getAppLanguage(this);
    }

    /**
     * 显示返回键
     *
     * @return
     */
    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_auto_cache:
                assert mPresenter != null;
                mPresenter.setAutoCacheState(isChecked);
                break;
            case R.id.cb_setting_no_image:
                assert mPresenter != null;
                mPresenter.setNoImageState(isChecked);
                if (isChecked) {
                    toast("网页将不显示图片");
                } else {
                    toast("网页将显示图片");
                }
                break;
            case R.id.cb_setting_night:
                assert mPresenter != null;
                mPresenter.setNightModeState(isChecked);
                RxBus.getInstance().post(new NightModeEvent(isChecked));
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.setting_language, R.id.setting_theme_color, R.id.setting_update, R.id.setting_clear_cache, R.id.setting_feedback})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_language:
                // 是否需要重启

                // 多语言
                BottomMenu.show(this, languageList, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        mTvLanguageType.setText(text);
                        //TODO 切换语言
                        switch (index) {
                            case 0:
                                restart = LanguagesManager.setSystemLanguage(getApplicationContext());
                                break;
                            case 1:
                                restart = LanguagesManager.setAppLanguage(getApplicationContext(), Locale.CHINA);
                                break;
                            case 2:
                                restart = LanguagesManager.setAppLanguage(getApplicationContext(), Locale.TAIWAN);
                                break;
                            case 3:
                                restart = LanguagesManager.setAppLanguage(getApplicationContext(), Locale.ENGLISH);
                                break;
                            default:
                                restart = false;
                                break;
                        }
                        if (restart) {
                            startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                            overridePendingTransition(R.anim.left_top_in, R.anim.right_top_out);
                            finish();
                        }
                    }
                });
                break;
            case R.id.setting_theme_color:
                //主题颜色
                break;
            case R.id.setting_update:
                //检查更新
                Beta.checkUpgrade();
                break;
            case R.id.setting_clear_cache:
                //清除缓存
                clearCache();
//                mTvCacheNum.setText(ACache.getCacheSize(cacheFile));
                mTvCacheNum.setText(cache);
                toast(R.string.setting_clear_cache_success);
                break;
            case R.id.setting_feedback:
                //意见反馈
                assert mPresenter != null;
                mPresenter.feedback(this, getString(R.string.choose_email));
                break;
            default:
                break;
        }
    }

    /**
     * 设置CheckBox是否选中
     *
     * @param checkBox
     */
    private void setChecked(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            //如果已被选中，则设置成未选中
            checkBox.setChecked(false);
        } else {
            //如果未被选中，则设置成已选中
            checkBox.setChecked(true);
        }
    }

    /**
     * 清除缓存
     */
    private void clearCache() {
//        ACache.deleteDir(cacheFile);
        CacheUtils.getInstance().clear();
    }
}
