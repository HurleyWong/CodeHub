package com.hurley.wanandroid.app;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/9 5:56 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class LoadType {

    /**
     * 刷新
     */
    public static final int TYPE_REFRESH_SUCCESS = 1;
    /**
     * 不刷新
     */
    public static final int TYPE_REFRESH_ERROR = 2;
    /**
     * 加载更多
     */
    public static final int TYPE_LOAD_MORE_SUCCESS = 3;
    /**
     * 不加载更多
     */
    public static final int TYPE_LOAD_MORE_ERROR = 4;

    @IntDef({TYPE_REFRESH_SUCCESS, TYPE_REFRESH_ERROR, TYPE_LOAD_MORE_SUCCESS, TYPE_LOAD_MORE_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface checker {
    }
}
