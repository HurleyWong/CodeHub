package com.hurley.wanandroid.utils;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/3 9:59 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 字符串相关工具类
 * </pre>
 */
public class StringUtils {

    /**
     * 泛型转化：object ==> map<String, String>
     * cast函数用于将某种数据类型的表达式显式转换为另一种数据类型
     * @param object            Object
     * @param <T>               转换得到的泛型对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }
}
