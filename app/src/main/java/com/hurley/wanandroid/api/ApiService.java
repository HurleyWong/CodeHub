package com.hurley.wanandroid.api;



import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.bean.ArticleBean;
import com.hurley.wanandroid.bean.BannerBean;
import com.hurley.wanandroid.bean.CollectArticleBean;
import com.hurley.wanandroid.bean.HotKeyBean;
import com.hurley.wanandroid.bean.NaviBean;
import com.hurley.wanandroid.bean.PageBean;
import com.hurley.wanandroid.bean.ProjectBean;
import com.hurley.wanandroid.bean.SystemBean;
import com.hurley.wanandroid.bean.UserBean;
import com.hurley.wanandroid.bean.WebsiteBean;
import com.hurley.wanandroid.bean.WxAccountBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午8:56
 *      github : https://github.com/HurleyJames
 *      desc   : 服务器接口类
 * </pre>
 */
public interface ApiService {

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     * @param username              用户名
     * @param password              密码
     * @return
     */
    @POST(UrlContainer.LOGIN)
    @FormUrlEncoded
    Observable<BaseBean<UserBean>> login(@Field("username") String username,
                                         @Field("password") String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     * @param username              用户名
     * @param password              密码
     * @param repassword            重复密码
     * @return
     */
    @POST(UrlContainer.REGISTER)
    @FormUrlEncoded
    Observable<BaseBean<UserBean>> register(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("repassword") String repassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     * @return
     */
    @GET(UrlContainer.LOGOUT)
    @FormUrlEncoded
    Observable<BaseBean<UserBean>> logout();

    /**
     * 首页文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param page                  页码
     * @return
     */
    @GET(UrlContainer.INDEX_ARTICLE_LIST)
    Observable<BaseBean<ArticleBean>> getIndexArticles(@Path("page") int page);

    /**
     * 首页Banner
     * http://www.wanandroid.com/banner/json
     * @return
     */
    @GET(UrlContainer.INDEX_BANNER)
    Observable<BaseBean<List<BannerBean>>> getIndexBanners();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     * @return
     */
    @GET(UrlContainer.WEBSITE)
    Observable<BaseBean<List<WebsiteBean>>> getWebsites();

    /**
     * 搜索热词
     * http://www.wanandroid.com//hotkey/json
     * @return
     */
    @GET(UrlContainer.HOT_KEY)
    Observable<BaseBean<List<HotKeyBean>>> getHotKeys();

    /**
     * 体系数据
     * http://www.wanandroid.com/tree/json
     * @return
     */
    @GET(UrlContainer.TREE)
    Observable<BaseBean<List<SystemBean>>> getSystem();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=60
     * @param page                  页码
     * @param cid                   分类的id，上述二级目录的id
     * @return
     */
    @GET(UrlContainer.TREE_ARTICLE_LIST)
    Observable<BaseBean<ArticleBean>> getSystemArticles(@Path("page") int page,
                                                                  @Query("cid") int cid);

    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     * @return
     */
    @GET(UrlContainer.NAVI)
    Observable<BaseBean<List<NaviBean>>> getNavigation();

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     * @return
     */
    @GET(UrlContainer.PROJECT_TREE)
    Observable<BaseBean<List<ProjectBean>>> getProject();

    /**
     * 项目列表数据（文章）
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * @param page
     * @param cid
     * @return
     */
    @GET(UrlContainer.PROJECT_LIST)
    Observable<BaseBean<PageBean<ArticleBean>>> getProjectArticles(@Path("page") int page,
                                                                   @Query("cid") int cid);

    /**
     * 最新项目tab
     * http://wanandroid.com/article/listproject/0/json
     * @param page                  页码
     * @return
     */
    @GET(UrlContainer.LATEST_PROJECT)
    Observable<BaseBean<PageBean<ArticleBean>>> getLatestProjectArticles(@Path("page") int page);

    /**
     * 收藏文章列表
     * http://www.wanandroid.com/lg/collect/list/0/json
     * @param page                  页码
     * @return
     */
    @GET(UrlContainer.COLLECT_ARTICLE_LIST)
    Observable<BaseBean<ArticleBean>> getCollectArticles(@Path("page") int page);

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * 成功则返回：
     *      "data": null,
     *      "errorCode": 0,
     *      "errorMsg": ""
     * @param id                    文章id
     * @return
     */
    @POST(UrlContainer.COLLECT_INSIDE_ARTICLE)
    Observable<BaseBean> collectInsideArticle(@Path("id") int id);

    /**
     * 收藏站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     * @param title                 标题
     * @param author                作者
     * @param link                  链接
     * @return
     */
    @POST(UrlContainer.COLLECT_OUTSIDE_ARTICLE)
    Observable<BaseBean<CollectArticleBean>> collectOutsideArticle(@Field("title") String title,
                                                                   @Field("author") String author,
                                                                   @Field("link") String link);

    /**
     * 从文章列表取消收藏
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * 2333为originId
     * 成功则返回：
     *      "data": null,
     *      "errorCode": 0,
     *      "errorMsg": ""
     * @param id
     * @param originId
     * @return
     */
    @POST(UrlContainer.UNCOLLECT_ARTICLE_1)
    Observable<BaseBean<String>> unCollectArticle1(@Path("id") int id,
                                                   @Field("originId") int originId);

    /**
     * 从我的收藏页面取消收藏
     * http://www.wanandroid.com/lg/uncollect/48032/json?originId=1166
     * 48032为id，1166为originId。如果是从站外收藏，则originId为-1
     * 成功则返回：
     *      "data": null,
     *      "errorCode": 0,
     *      "errorMsg": ""
     * @param id
     * @param originId
     * @return
     */
    @POST(UrlContainer.UNCOLLECT_ARTICLE_2)
    Observable<BaseBean> unCollectArticle2(@Path("id") int id,
                                                   @Field("originId") int originId);

    /**
     * 收藏网站列表
     * http://www.wanandroid.com/lg/collect/usertools/json
     * @return
     */
    @GET(UrlContainer.COLLECT_WEBSITE_LIST)
    Observable<BaseBean<List<WebsiteBean>>> getCollectWebsite();

    /**
     * 收藏网址
     * http://www.wanandroid.com/lg/collect/addtool/json
     * @param name                  标题
     * @param link                  链接
     * @return
     */
    @POST(UrlContainer.COLLECT_WEBSITE)
    Observable<BaseBean<String>> collectWebsite(@Field("name") String name,
                                                @Field("link") String link);

    /**
     * 编辑收藏网址
     * http://www.wanandroid.com/lg/collect/updatetool/json
     * @param id                    文章id
     * @param name                  标题
     * @param link                  链接
     * @return
     */
    @POST(UrlContainer.EDIT_COLLECT_WEBSITE)
    Observable<BaseBean<String>> editWebsite(@Path("id") int id,
                                             @Field("name") String name,
                                             @Field("link") String link);

    /**
     * 删除收藏网址
     * http://www.wanandroid.com/lg/collect/deletetool/json
     * @param id                    文章id
     * @return
     */
    @POST(UrlContainer.DEL_COLLECT_WEBSITE)
    Observable<BaseBean<String>> delWebsite(@Path("id") int id);

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page                  页码
     * @param k                     关键字
     * @return
     */
    @POST(UrlContainer.SEARCH)
    @FormUrlEncoded
    Observable<BaseBean<PageBean<ArticleBean>>> getSearchArticles(@Path("page") int page,
                                                                  @Field("k") String k);

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     * @return
     */
    @GET(UrlContainer.WXARTICLE_LIST)
    Observable<BaseBean<List<WxAccountBean>>> getWxAccounts();

    /**
     * 查看某个公众号历史数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     * @param id                    公众号id
     * @param page                  公众号页码
     * @return
     */
    @GET(UrlContainer.WXARTICLE_HISTORY)
    Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsHistory(@Path("id") int id,
                                                                     @Path("page") int page);

    /**
     * 在某个公众号中搜索历史文章
     * http://wanandroid.com/wxarticle/list/405/1/json?k=Java
     * @param id                    公众号id
     * @param page                  页码
     * @param k                     关键字
     * @return
     */
    @GET(UrlContainer.WXARTICLE_SEARCH)
    Observable<BaseBean<PageBean<ArticleBean>>> getWxAccountsBySearch(@Path("id") int id,
                                                                      @Path("page") int page,
                                                                      @Query("k") String k);
}
