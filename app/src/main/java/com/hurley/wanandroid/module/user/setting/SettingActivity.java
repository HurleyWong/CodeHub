package com.hurley.wanandroid.module.user.setting;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.app.ACache;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.event.NightModeEvent;
import com.hurley.wanandroid.net.callback.RxBus;
import com.kongzue.dialog.v2.DialogSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_KONGZUE;

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

    /**
     * 语言种类
     */
    List<String> languageList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        cacheFile = new File(Constants.PATH_CACHE);
        mTvCacheNum.setText(ACache.getCacheSize(cacheFile));
        LogUtils.e(cacheFile);
        mCbAutoCache.setChecked(mPresenter.getAutoCacheState());
        mCbNoImage.setChecked(mPresenter.getNoImageState());
        mCbNight.setChecked(mPresenter.getNightModeState());
        //设置监听事件
        mCbAutoCache.setOnCheckedChangeListener(this);
        mCbNoImage.setOnCheckedChangeListener(this);
        mCbNight.setOnCheckedChangeListener(this);

        //Dialog风格为Kongzue风格
        DialogSettings.style = STYLE_KONGZUE;
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_auto_cache:
                mPresenter.setAutoCacheState(isChecked);
                break;
            case R.id.cb_setting_no_image:
                mPresenter.setNoImageState(isChecked);
                break;
            case R.id.cb_setting_night:
                mPresenter.setNightModeState(isChecked);
                RxBus.getInstance().post(new NightModeEvent(isChecked));
                break;
            default:
                break;
        }
    }

    /**
     * 设置CheckBox是否选中
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

}
