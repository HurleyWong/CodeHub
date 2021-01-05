package com.hurley.codehub.util;

import android.graphics.Color;

import com.hurley.codehub.app.Constants;

import java.util.Random;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 15:39
 *      github  : https://github.com/HurleyJames
 *      desc    : 颜色工具类
 * </pre>
 */
public class ColorUtils {

    public static int randomColor() {
        Random random = new Random();
        // 0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        // 0-190
        int green =random.nextInt(150);
        // 0-190
        int blue =random.nextInt(150);
        // 使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Constants.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constants.TAB_COLORS[position];
    }
}
