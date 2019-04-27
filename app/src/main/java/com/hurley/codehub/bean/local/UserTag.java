package com.hurley.codehub.bean.local;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 18:18
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
public class UserTag {

    private int userid;
    private String title;
    private String subTitle;
    private int image;
    private boolean isFollowed;

    public UserTag() {

    }

    public UserTag(int userid, String title) {
        this.userid = userid;
        this.title = title;
    }

    public UserTag(int userid, String title, String subTitle) {
        this.userid = userid;
        this.title = title;
        this.subTitle = subTitle;
    }

    public UserTag(int userid, String title, String subTitle, int image) {
        this.userid = userid;
        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
    }

    public UserTag(int userid, String title, boolean isFollowed) {
        this.userid = userid;
        this.title = title;
        this.isFollowed = isFollowed;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }
}
