package com.hurley.codehub.bean.local;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-25 16:50
 *      github  : https://github.com/HurleyJames
 *      desc    : 从自己数据库获得的推荐文章类目的实体类
 * </pre>
 */
public class Chapter {


    /**
     * errorCode : 0
     * errorMsg :
     * data : [{"userid":1,"chapterid":60,"duration":2},{"userid":2,"chapterid":228,"duration":3}]
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : 1
         * chapterid : 60
         * duration : 2
         */

        private int userid;
        private int chapterid;
        private int duration;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getChapterid() {
            return chapterid;
        }

        public void setChapterid(int chapterid) {
            this.chapterid = chapterid;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}
