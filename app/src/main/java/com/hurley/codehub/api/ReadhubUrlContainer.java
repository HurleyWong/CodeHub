package com.hurley.codehub.api;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/31 12:47 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Readhub Api接口地址类
 * </pre>
 */
public class ReadhubUrlContainer {

    private static final String TAG = "ReadhubUrlContainer";

    public static final String baseUrl = "https://api.readhub.cn/";

    /**
     * 热门话题
     */
    public static final String TOPIC = "topic";

    /**
     * 热门话题细节
     */
    public static final String TOPIC_DETAIL = "topic/{topic_id}";

    /**
     * 科技动态
     */
    public static final String NEWS = "news";

    /**
     * 区块链
     */
    public static final String BLOCK_CHAIN = "blockchain";

    /**
     * 开发者资讯
     */
    public static final String TECH_NEWS = "technews";
}
