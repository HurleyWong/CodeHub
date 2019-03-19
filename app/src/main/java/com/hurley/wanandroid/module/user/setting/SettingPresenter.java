package com.hurley.wanandroid.module.user.setting;

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
        return false;
    }

    @Override
    public void setAutoCacheState(boolean b) {

    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public void setNoImageState(boolean b) {

    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightMode(b);
    }
}
