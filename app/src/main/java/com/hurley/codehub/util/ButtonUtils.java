package com.hurley.codehub.util;

import android.support.v4.content.ContextCompat;

import com.blankj.utilcode.util.ConvertUtils;
import com.deadline.statebutton.StateButton;
import com.hurley.codehub.R;
import com.hurley.codehub.app.App;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-30 17:24
 *      github  : https://github.com/HurleyJames
 *      desc    : 按钮工具类
 * </pre>
 */
public class ButtonUtils {

    /**
     * 设置StateButton样式1
     * @param button
     */
    public static void setButtonStyle1(StateButton button) {
        button.setText(App.getAppContext().getString(R.string.follow));
        button.setNormalTextColor(ContextCompat.getColor(App.getAppContext(), R.color.colorAccent));
        button.setNormalStrokeWidth(ConvertUtils.dp2px(2));
        button.setNormalStrokeColor(ContextCompat.getColor(App.getAppContext(), R.color.colorAccent));
        button.setNormalBackgroundColor(ContextCompat.getColor(App.getAppContext(), R.color.white));
        button.setPressedTextColor(ContextCompat.getColor(App.getAppContext(), R.color.colorButtonPressed));
        button.setPressedStrokeWidth(ConvertUtils.dp2px(2));
        button.setPressedStrokeColor(ContextCompat.getColor(App.getAppContext(), R.color.colorButtonPressed));
        button.setPressedBackgroundColor(ContextCompat.getColor(App.getAppContext(), R.color.colorButtonDisable));
    }

    /**
     * 设置StateButton样式2
     * @param button
     */
    public static void setButtonStyle2(StateButton button) {
        button.setText(App.getAppContext().getString(R.string.followed));
        button.setNormalTextColor(ContextCompat.getColor(App.getAppContext(), R.color.white));
        button.setNormalStrokeWidth(ConvertUtils.dp2px(2));
        button.setNormalStrokeColor(ContextCompat.getColor(App.getAppContext(), R.color.colorAccent));
        button.setNormalBackgroundColor(ContextCompat.getColor(App.getAppContext(), R.color.colorAccent));
        button.setPressedTextColor(ContextCompat.getColor(App.getAppContext(), R.color.white));
        button.setPressedStrokeWidth(ConvertUtils.dp2px(2));
        button.setPressedStrokeColor(ContextCompat.getColor(App.getAppContext(), R.color.colorButtonPressed));
        button.setPressedBackgroundColor(ContextCompat.getColor(App.getAppContext(), R.color.colorButtonPressed));
    }
}
