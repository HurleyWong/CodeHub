package com.hurley.codehub.bean.readhub;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 1:55 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 实体类基类（网络请求返回的代码）
 * </pre>
 */
public class BaseBean<T> {

    private List<T> data;
    private int pageSize;
    private int totalItems;
    private int totalPages;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


}
