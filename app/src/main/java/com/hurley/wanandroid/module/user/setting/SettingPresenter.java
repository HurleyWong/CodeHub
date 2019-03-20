package com.hurley.wanandroid.module.user.setting;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.net.DataManager;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/19 9:46 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 设置 Presenter类
 * </pre>
 */
public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SettingPresenter() {
    }

    @Override
    public boolean getAutoCacheState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.AUTO_CACHE);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.AUTO_CACHE, b);
    }

    @Override
    public boolean getNoImageState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.NO_IMAGE);
    }

    @Override
    public void setNoImageState(boolean b) {
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.NO_IMAGE, b);
    }

    @Override
    public void setNightModeState(boolean b) {
        SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.NIGHT_MODE, b);
    }

    @Override
    public boolean getNightModeState() {
        return SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getBoolean(Constants.NIGHT_MODE);
    }
}
