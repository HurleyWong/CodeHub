package com.hurley.codehub.bean.common;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/21 下午7:24
 *      github : https://github.com/HurleyJames
 *      desc   : 开源协议实体类
 * </pre>
 */
public class OpenSourceBean {

    private String name;
    private String author;
    private String detail;

    public OpenSourceBean() {

    }

    public OpenSourceBean(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public OpenSourceBean(String name, String author, String detail) {
        this.name = name;
        this.author = author;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
