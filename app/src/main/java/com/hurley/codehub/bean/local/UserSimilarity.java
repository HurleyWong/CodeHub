package com.hurley.codehub.bean.local;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-05-08 22:34
 *      github  : https://github.com/HurleyJames
 *      desc    : 用户相似度实体类
 * </pre>
 */
public class UserSimilarity {

    /**
     * userid : 20649
     * userrefid : 4
     * similarity : 0.5311611
     */

    private int userid;
    private int userrefid;
    private double similarity;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserrefid() {
        return userrefid;
    }

    public void setUserrefid(int userrefid) {
        this.userrefid = userrefid;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

}
