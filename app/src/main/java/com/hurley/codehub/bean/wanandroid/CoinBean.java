package com.hurley.codehub.bean.wanandroid;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-09-11 14:05
 *      github  : https://github.com/HurleyJames
 *      desc    : 积分实体类
 * </pre>
 */
public class CoinBean {

    /**
     * curPage : 1
     * datas : [{"coinCount":797,"rank":0,"userId":0,"username":"S**24n"},{"coinCount":770,"rank":0,"userId":0,"username":"1**08491840"},{"coinCount":770,"rank":0,"userId":0,"username":"r**one"},{"coinCount":770,"rank":0,"userId":0,"username":"g**eii"},{"coinCount":758,"rank":0,"userId":0,"username":"陈**啦啦啦"},{"coinCount":758,"rank":0,"userId":0,"username":"j**gbin"},{"coinCount":758,"rank":0,"userId":0,"username":"x**oyang"},{"coinCount":746,"rank":0,"userId":0,"username":"c**huah"},{"coinCount":746,"rank":0,"userId":0,"username":"w**.wanandroid.com"},{"coinCount":746,"rank":0,"userId":0,"username":"a**ian"},{"coinCount":745,"rank":0,"userId":0,"username":"l**64301766"},{"coinCount":735,"rank":0,"userId":0,"username":"l**hehan"},{"coinCount":735,"rank":0,"userId":0,"username":"7**502274@qq.com"},{"coinCount":735,"rank":0,"userId":0,"username":"i**Cola"},{"coinCount":735,"rank":0,"userId":0,"username":"l**shifu"},{"coinCount":735,"rank":0,"userId":0,"username":"w**lwaywang6"},{"coinCount":696,"rank":0,"userId":0,"username":"c**01220122"},{"coinCount":696,"rank":0,"userId":0,"username":"1**23822235"},{"coinCount":658,"rank":0,"userId":0,"username":"1**88468150"},{"coinCount":658,"rank":0,"userId":0,"username":"S**iWanZi"},{"coinCount":658,"rank":0,"userId":0,"username":"l**changwen"},{"coinCount":658,"rank":0,"userId":0,"username":"t**g111"},{"coinCount":658,"rank":0,"userId":0,"username":"1**22715239"},{"coinCount":658,"rank":0,"userId":0,"username":"l**enshan"},{"coinCount":658,"rank":0,"userId":0,"username":"S**Brother"},{"coinCount":621,"rank":0,"userId":0,"username":"E**n_ssk"},{"coinCount":621,"rank":0,"userId":0,"username":"o**shot"},{"coinCount":621,"rank":0,"userId":0,"username":"S**phenCurry"},{"coinCount":621,"rank":0,"userId":0,"username":"A**ilEyon"},{"coinCount":621,"rank":0,"userId":0,"username":"m**qitian"}]
     * offset : 0
     * over : false
     * pageCount : 122
     * size : 30
     * total : 3647
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * coinCount : 797
         * rank : 0
         * userId : 0
         * username : S**24n
         */

        private int coinCount;
        private int rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
