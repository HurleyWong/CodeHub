package com.hurley.wanandroid.module.user.analysis;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hurley.wanandroid.api.ApiService;
import com.hurley.wanandroid.app.Constants;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.base.BasePresenter;
import com.hurley.wanandroid.net.RetrofitManager;
import com.hurley.wanandroid.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/23 10:29 AM
 *      github  : https://github.com/HurleyJames
 *      desc    : 分析 Presenter类
 * </pre>
 */
public class AnalysisPresenter extends BasePresenter<AnalysisContract.View> implements AnalysisContract.Presenter{

    /**
     * 页数
     */
    private int mPage;

    private int totalPage = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.TOTAL_PAGE);

    private int mUI = 0;
    private int mJNI = 0;
    private int mComponents = 0;
    private int mCtrls = 0;
    private int mCommCtrls = 0;
    private int mProjects = 0;
    private int mData = 0;
    private int mHard = 0;
    private int mKnowledge = 0;
    private int mImage = 0;
    private int mPlatforms = 0;
    private int mKotlin = 0;
    private int mJetpack = 0;
    private int mAnim = 0;
    private int mFramework = 0;
    private int mJava = 0;
    private int mMedia = 0;
    private int mNet = 0;
    private int mDev = 0;


    @Inject
    public AnalysisPresenter() {}

    @SuppressLint("CheckResult")
    @Override
    public void getNearbyArticles() {
        LogUtils.e("总页数：" + totalPage);
        for (mPage = 0; mPage < totalPage; mPage ++) {
            RetrofitManager.create(ApiService.class)
                    .getIndexArticles(mPage)
                    .compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(response -> {
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            for (int i = 0; i < response.getData().getSize(); i ++) {
                                String articleDate = response.getData().getDatas().get(i).getNiceDate();
                                String articleTitle = response.getData().getDatas().get(i).getTitle();
                                String articleCharpter = response.getData().getDatas().get(i).getChapterName();
                                String articleSuperCharpter = response.getData().getDatas().get(i).getsuperChapterName();
                                //LogUtils.e("文章日期：" + response.getData().getDatas().get(i).getNiceDate());
                                if (!RegexUtils.isDate(articleDate)) {
                                    //如果不是正确的日期格式，即以几天前的形式显示
                                    LogUtils.e("标题：" + articleTitle + "，体系：" + articleCharpter + "，模块：" + articleSuperCharpter);
                                }

                            }
                        } else {
                            mView.showFailed(response.getErrorMsg());
                        }
                    }, throwable -> mView.showFailed(throwable.getMessage()));
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllArticles() {
        for (mPage = 0; mPage < totalPage - 300; mPage ++) {
            RetrofitManager.create(ApiService.class)
                    .getIndexArticles(mPage)
                    .compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(response -> {
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            for (int i = 0; i < response.getData().getSize(); i ++) {
                                String articleDate = response.getData().getDatas().get(i).getNiceDate();
                                String articleTitle = response.getData().getDatas().get(i).getTitle();
                                String articleCharpter = response.getData().getDatas().get(i).getChapterName();
                                String articleSuperCharpter = response.getData().getDatas().get(i).getsuperChapterName();
                                //计算与今天相隔的天数
                                if (RegexUtils.isDate(articleDate)) {
                                    //如果是正确的日期格式
                                    String strInterDays = TimeUtils.getFitTimeSpanByNow(articleDate+ " 00:00:00", 1);
                                    int intervalDays = Integer.parseInt(strInterDays.substring(0, strInterDays.length() - 1));
                                    if (intervalDays <= 7) {
                                        //获取一周内的文章
                                        LogUtils.e("日期：" + articleDate);
                                        switch (articleSuperCharpter) {
                                            case Constants.UI:
                                                mUI ++;
                                                break;
                                            case Constants.JNI:
                                                mJNI ++;
                                                break;
                                            case Constants.COMPONENTS:
                                                mComponents ++;
                                                break;
                                            case Constants.COMM_CTRLS:
                                                mCommCtrls ++;
                                                break;
                                            case Constants.CTRLS:
                                                mCtrls ++;
                                                break;
                                            case Constants.PROJECTS:
                                                mProjects ++;
                                                break;
                                            case Constants.DATA:
                                                mData ++;
                                                break;
                                            case Constants.HARDWARE:
                                                mHard ++;
                                                break;
                                            case Constants.KNOWLEDAGE:
                                                mKnowledge ++;
                                                break;
                                            case Constants.IMAGE:
                                                mImage ++;
                                                break;
                                            case Constants.PLATFORMS:
                                                mPlatforms ++;
                                                break;
                                            case Constants.KOTLIN:
                                                mKotlin ++;
                                                break;
                                            case Constants.JETPACK:
                                                mJetpack ++;
                                                break;
                                            case Constants.ANIM:
                                                mAnim ++;
                                                break;
                                            case Constants.FRAMEWORK:
                                                mFramework ++;
                                                break;
                                            case Constants.JAVA:
                                                mJava ++;
                                                break;
                                            case Constants.MEDIA:
                                                mMedia ++;
                                                break;
                                            case Constants.NET:
                                                mNet ++;
                                                break;
                                            case Constants.DEV:
                                                mDev++;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            //将文章数量存储到首选项中
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.UI, mUI);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.JNI, mJNI);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.COMPONENTS, mComponents);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.COMM_CTRLS, mCommCtrls);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.CTRLS, mCtrls);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.PROJECTS, mProjects);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.DATA, mData);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.HARDWARE, mHard);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.KNOWLEDAGE, mKnowledge);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.IMAGE, mImage);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.PLATFORMS, mPlatforms);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.KOTLIN, mKotlin);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.JETPACK, mJetpack);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.ANIM, mAnim);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.FRAMEWORK, mFramework);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.JAVA, mJava);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.MEDIA, mMedia);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.NET, mNet);
                            SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).put(Constants.DEV, mDev);
                        } else {
                            mView.showFailed(response.getErrorMsg());
                        }
                    }, throwable -> mView.showFailed(throwable.getMessage()));
        }
    }
}
