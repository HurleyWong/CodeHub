package com.hurley.codehub.bean.wanandroid;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/26 2:07 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分页列表实体类
 * </pre>
 */
public class PageBean<T> {

    /**
     * curPage : 1
     * offset : 0
     * over : false
     * pageCount : 2
     * size : 20
     * total : 38
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}