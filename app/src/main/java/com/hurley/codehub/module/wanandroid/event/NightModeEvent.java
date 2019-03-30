package com.hurley.codehub.module.wanandroid.event;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/19 10:07 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 夜间模式事件类
 * </pre>
 */
public class NightModeEvent {

    private boolean isNightMode;

    public NightModeEvent(boolean isNightMode) {
        this.isNightMode = isNightMode;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }
}
