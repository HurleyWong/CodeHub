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

    private String id;
    private String createdAt;
    private String updatedAt;
    private String publishDate;
    private String summary;
    private String title;
    private String timeline;
    private String order;

    private List<NewsBean> newsBeanList;
    private Extra extra;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<NewsBean> getNewsBeanList() {
        return newsBeanList;
    }

    public void setNewsBeanList(List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public static class Extra {
        boolean instantView;
    }

}
