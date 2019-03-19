package com.hurley.wanandroid.helper.db;


import com.hurley.wanandroid.bean.HistoryBean;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 2:46 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 数据库基本操作的接口
 * </pre>
 */
public interface DbHelper {

    /**
     * 添加历史记录
     * @param data
     * @return
     */
    List<HistoryBean> addHistory(String data);

    /**
     * 清除历史记录
     */
    void clearHistory();

    /**
     * 加载所有历史记录
     * @return
     */
    List<HistoryBean> loadAllHistory();
}
