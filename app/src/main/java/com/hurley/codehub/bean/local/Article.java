package com.hurley.codehub.bean.local;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-23 10:17
 *      github  : https://github.com/HurleyJames
 *      desc    : 保存到自己数据库的文章实体类
 * </pre>
 */
public class Article {

    /**
     * id : 21
     * userid : 2
     * title : a
     * author : a
     * chapterid : 7
     * chaptername : a
     * superchapterid : 7
     * superchaptername : a
     * nicedate : a
     * link : a
     * duration : 5
     */

    private int id;
    private int userid;
    private String title;
    private String author;
    private int chapterid;
    private String chaptername;
    private int superchapterid;
    private String superchaptername;
    private String nicedate;
    private String link;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterid() {
        return chapterid;
    }

    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public int getSuperchapterid() {
        return superchapterid;
    }

    public void setSuperchapterid(int superchapterid) {
        this.superchapterid = superchapterid;
    }

    public String getSuperchaptername() {
        return superchaptername;
    }

    public void setSuperchaptername(String superchaptername) {
        this.superchaptername = superchaptername;
    }

    public String getNicedate() {
        return nicedate;
    }

    public void setNicedate(String nicedate) {
        this.nicedate = nicedate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
