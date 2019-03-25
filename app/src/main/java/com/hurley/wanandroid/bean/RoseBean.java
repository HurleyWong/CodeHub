package com.hurley.wanandroid.bean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 3:43 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 南丁格尔玫瑰图实体类
 * </pre>
 */
public class RoseBean {

    private float count;
    private String ClassName;

    public RoseBean() {
    }

    public RoseBean(float count, String className) {
        this.count = count;
        ClassName = className;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }
}
