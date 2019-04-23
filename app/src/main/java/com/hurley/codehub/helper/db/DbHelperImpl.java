package com.hurley.codehub.helper.db;


import com.hurley.codehub.app.App;
import com.hurley.codehub.bean.common.HistoryBean;
import com.hurley.codehub.dao.DaoSession;
import com.hurley.codehub.dao.HistoryBeanDao;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/27 5:32 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 数据库基本操作的实现细节类
 * </pre>
 */
public class DbHelperImpl implements DbHelper{

    private static final int HISTORY_LIST_SIZE = 10;

    private DaoSession mDaoSession;
    private List<HistoryBean> mHistoryBeanList;
    private String data;
    private HistoryBean mHistoryBean;

    @Inject
    DbHelperImpl() {
        mDaoSession = App.getInstance().getDaoSession();
    }

    @Override
    public List<HistoryBean> addHistory(String data) {
        return null;
    }

    @Override
    public void clearHistory() {
        getHistoryBeanDao().deleteAll();
    }

    @Override
    public List<HistoryBean> loadAllHistory() {
        return getHistoryBeanDao().loadAll();
    }

    private void createHistory() {
        mHistoryBean = new HistoryBean();
        mHistoryBean.setDate(System.currentTimeMillis());
        mHistoryBean.setData(data);
    }

    private HistoryBeanDao getHistoryBeanDao() {
        return mDaoSession.getHistoryBeanDao();
    }

    private void getHistoryBeanList() {
        mHistoryBeanList = getHistoryBeanDao().loadAll();
    }

    /**
     * 历史数据前移
     * 如果输入查询的数据已经存在于历史记录中，则不会再添加该新历史记录，而是将已存在的历史记录移到第一条历史记录即可
     * @return
     */
    private boolean historyForward() {
        //重复搜索时进行历史记录前移
        Iterator<HistoryBean> iterator = mHistoryBeanList.iterator();
        //不在foreach循环中进行元素的remove、add操作，使用Iterator模式操作
        while (iterator.hasNext()) {
            HistoryBean historyBean = iterator.next();
            if (historyBean.getData().equals(data)) {
                mHistoryBeanList.remove(historyBean);
                mHistoryBeanList.add(historyBean);
                getHistoryBeanDao().deleteAll();
                getHistoryBeanDao().insertInTx(mHistoryBeanList);
                return true;
            }
        }
        return false;
    }
}
