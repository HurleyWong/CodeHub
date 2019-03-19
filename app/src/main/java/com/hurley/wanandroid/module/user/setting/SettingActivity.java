package com.hurley.wanandroid.module.user.setting;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.hurley.wanandroid.R;
import com.hurley.wanandroid.api.PathContainer;
import com.hurley.wanandroid.base.BaseActivity;
import com.hurley.wanandroid.event.NightModeEvent;
import com.hurley.wanandroid.net.callback.RxBus;
import com.kongzue.dialog.v2.DialogSettings;

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
                break;
            case R.id.cb_setting_no_image:
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
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
        }
    }


}
