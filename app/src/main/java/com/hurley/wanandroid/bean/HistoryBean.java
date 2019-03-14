package com.hurley.wanandroid.bean;



/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 3:41 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 搜索历史实体类
 * </pre>
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Entity 用于标识这是一个需要Greendao帮我们生成代码的bean
 */
@Entity
public class HistoryBean {

    /**
     * 主键Long型，通过@Id(autoincrement = true)设置自增长
     */
    @Id(autoincrement = true)
    private Long id;

    private long date;

    private String data;

    @Generated(hash = 1117419281)
    public HistoryBean(Long id, long date, String data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }

    @Generated(hash = 48590348)
    public HistoryBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
