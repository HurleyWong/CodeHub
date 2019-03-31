package com.hurley.codehub.bean.readhub;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 2:02 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 科技动态实体类
 * </pre>
 */
public class NewsBean {

    private String id;
    private String title;
    private String summary;
    private String summaryAuto;
    private String url;
    private String mobileUrl;
    private String siteName;
    private String language;
    private String authorName;
    private String publishDate;

    /**
     * 以下为Topic的List<News>的属性
     */
    private int statementType;
    private int duplicateId;

    public int getStatementType() {
        return statementType;
    }

    public void setStatementType(int statementType) {
        this.statementType = statementType;
    }

    public int getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(int duplicateId) {
        this.duplicateId = duplicateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryAuto() {
        return summaryAuto;
    }

    public void setSummaryAuto(String summaryAuto) {
        this.summaryAuto = summaryAuto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
