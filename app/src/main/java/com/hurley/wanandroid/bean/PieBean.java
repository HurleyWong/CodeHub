package com.hurley.wanandroid.bean;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/24 2:24 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 饼状图实体类
 * </pre>
 */
public class PieBean {

    private float Numner;
    private String Name;

    public PieBean() {
    }

    public PieBean(float Numner, String Name) {
        this.Numner = Numner;
        this.Name = Name;
    }

    public float getNumner() {
        return Numner;
    }

    public void setNumner(float numner) {
        Numner = numner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
