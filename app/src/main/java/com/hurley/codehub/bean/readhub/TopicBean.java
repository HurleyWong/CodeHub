package com.hurley.codehub.bean.readhub;

import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 2:08 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 热门话题实体类
 * </pre>
 */
public class TopicBean {

    /**
     * id : 83Ydbh1kw1A
     * newsArray : [{"id":3263719,"url":"https://tech.ifeng.com/c/83yCEa1mQHZ","title":"黄光裕开工首日内部讲话：力争用18个月使企业恢复原有的市场地位","siteName":"凤凰科技","mobileUrl":"https://tech.ifeng.com/c/83yCEa1mQHZ","autherName":"","duplicateId":2,"publishDate":"2021-02-18T12:56:33.000Z","language":"zh-cn","hasInstantView":true,"statementType":1},{"id":3263733,"url":"https://www.36kr.com/p/1103934714137092","title":"黄光裕最新讲话|力争用未来18个月的时间，使国美恢复原有的市场地位","siteName":"36Kr","mobileUrl":"https://www.36kr.com/p/1103934714137092","autherName":null,"duplicateId":3,"publishDate":"2021-02-18T13:01:02.871Z","language":"zh-cn","hasInstantView":true,"statementType":1},{"id":3263720,"url":"http://www.iheima.com/zixun/2021/0218/312778.shtml","title":"黄光裕出狱后首发声：力争用18个月恢复国美原有的市场地位","siteName":"i黑马","mobileUrl":"http://www.iheima.com/zixun/2021/0218/312778.shtml","autherName":null,"duplicateId":4,"publishDate":"2021-02-18T13:03:32.000Z","language":"zh-cn","hasInstantView":true,"statementType":1}]
     * createdAt : 2021-02-18T13:17:00.030Z
     * eventData : []
     * publishDate : 2021-02-18T13:20:17.211Z
     * summary : 2月18日，黄光裕在国美控股集团高管会上表示，“希望通过我们的奋斗，我们的努力，力争用未来18个月的时间，使企业恢复原有的市场地位。”
     * title : 黄光裕出狱后首发声：力争用18个月恢复国美原有的市场地位
     * updatedAt : 2021-02-18T14:02:17.434Z
     * timeline : 7LwKSORt5SN
     * order : 332800
     * hasInstantView : true
     * extra : {"instantView":true}
     */

    private String id;
    private String createdAt;
    private String publishDate;
    private String summary;
    private String title;
    private String updatedAt;
    private String timeline;
    private int order;
    private boolean hasInstantView;
    private ExtraBean extra;
    private List<NewsArrayBean> newsArray;
    private List<?> eventData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isHasInstantView() {
        return hasInstantView;
    }

    public void setHasInstantView(boolean hasInstantView) {
        this.hasInstantView = hasInstantView;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public List<NewsArrayBean> getNewsArray() {
        return newsArray;
    }

    public void setNewsArray(List<NewsArrayBean> newsArray) {
        this.newsArray = newsArray;
    }

    public List<?> getEventData() {
        return eventData;
    }

    public void setEventData(List<?> eventData) {
        this.eventData = eventData;
    }

    public static class ExtraBean {
        /**
         * instantView : true
         */

        private boolean instantView;

        public boolean isInstantView() {
            return instantView;
        }

        public void setInstantView(boolean instantView) {
            this.instantView = instantView;
        }
    }

    public static class NewsArrayBean {
        /**
         * id : 3263719
         * url : https://tech.ifeng.com/c/83yCEa1mQHZ
         * title : 黄光裕开工首日内部讲话：力争用18个月使企业恢复原有的市场地位
         * siteName : 凤凰科技
         * mobileUrl : https://tech.ifeng.com/c/83yCEa1mQHZ
         * autherName :
         * duplicateId : 2
         * publishDate : 2021-02-18T12:56:33.000Z
         * language : zh-cn
         * hasInstantView : true
         * statementType : 1
         */

        private int id;
        private String url;
        private String title;
        private String siteName;
        private String mobileUrl;
        private String autherName;
        private int duplicateId;
        private String publishDate;
        private String language;
        private boolean hasInstantView;
        private int statementType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getMobileUrl() {
            return mobileUrl;
        }

        public void setMobileUrl(String mobileUrl) {
            this.mobileUrl = mobileUrl;
        }

        public String getAutherName() {
            return autherName;
        }

        public void setAutherName(String autherName) {
            this.autherName = autherName;
        }

        public int getDuplicateId() {
            return duplicateId;
        }

        public void setDuplicateId(int duplicateId) {
            this.duplicateId = duplicateId;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public boolean isHasInstantView() {
            return hasInstantView;
        }

        public void setHasInstantView(boolean hasInstantView) {
            this.hasInstantView = hasInstantView;
        }

        public int getStatementType() {
            return statementType;
        }

        public void setStatementType(int statementType) {
            this.statementType = statementType;
        }
    }
}
