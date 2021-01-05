package com.hurley.codehub.widget;

import android.view.animation.Interpolator;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/17 下午11:03
 *      github : https://github.com/HurleyJames
 *      desc   : 插值函数
 * </pre>
 */
public class ElasticOutInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float input) {
        if (input == 0) {
            return 0;
        }
        if (input >= 1) {
            return 1;
        }
        float p = .3f;
        float s = p / 4;
        return ((float) Math.pow(2, -10 * input) * (float) Math.sin((input - s) * (2 * (float) Math.PI) / p) + 1);
    }
}
