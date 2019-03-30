package com.hurley.codehub.module.wanandroid.core.user.analysis;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.hurley.codehub.api.WanAndroidApiService;
import com.hurley.codehub.app.Constants;
import com.hurley.codehub.base.BaseBean;
import com.hurley.codehub.base.BasePresenter;
import com.hurley.codehub.bean.wanandroid.ArticleNumBean;
import com.hurley.codehub.net.RetrofitManager;
import com.hurley.codehub.net.callback.RxSchedulers;

import javax.inject.Inject;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/25 5:52 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 加载数据 Presenter类
 * </pre>
 */
public class LoadingDataPresenter extends BasePresenter<LoadingDataContract.View> implements LoadingDataContract.Presenter{

    /**
     * 页数
     */
    private int mPage;

    private int totalPage = SPUtils.getInstance(Constants.MY_SHARED_PREFERENCE).getInt(Constants.TOTAL_PAGE);

    /**
     * 一周数据
     */
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

    /**
     * 一月数据
     */
    private int mUIMonth = 0;
    private int mJNIMonth = 0;
    private int mComponentsMonth = 0;
    private int mCtrlsMonth = 0;
    private int mCommCtrlsMonth = 0;
    private int mProjectsMonth = 0;
    private int mDataMonth = 0;
    private int mHardMonth = 0;
    private int mKnowledgeMonth = 0;
    private int mImageMonth = 0;
    private int mPlatformsMonth = 0;
    private int mKotlinMonth = 0;
    private int mJetpackMonth = 0;
    private int mAnimMonth = 0;
    private int mFrameworkMonth = 0;
    private int mJavaMonth = 0;
    private int mMediaMonth = 0;
    private int mNetMonth = 0;
    private int mDevMonth = 0;

    private String articleDate;
    private String articleTitle;
    private String articleChapter;
    private String articleSuperChapter;

    @Inject
    public LoadingDataPresenter() {}

    @SuppressLint("CheckResult")
    @Override
    public void getAllArticles() {
        for (mPage = 0; mPage < totalPage; mPage ++) {
            RetrofitManager.create(WanAndroidApiService.class)
                    .getIndexArticles(mPage)
                    .compose(RxSchedulers.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(response -> {
                        if (response.getErrorCode() == BaseBean.SUCCESS) {
                            for (int i = 0; i < response.getData().getSize(); i ++) {
                                articleDate = response.getData().getDatas().get(i).getNiceDate();
                                articleTitle = response.getData().getDatas().get(i).getTitle();
                                articleChapter = response.getData().getDatas().get(i).getChapterName();
                                articleSuperChapter = response.getData().getDatas().get(i).getsuperChapterName();
                                //计算与今天相隔的天数
                                if (RegexUtils.isDate(articleDate)) {
                                    //如果是正确的日期格式
                                    String strInterDays = TimeUtils.getFitTimeSpanByNow(articleDate+ " 00:00:00", 1);
                                    int intervalDays = Integer.parseInt(strInterDays.substring(0, strInterDays.length() - 1));
                                    if (intervalDays <= 7) {
                                        //获取一周内的文章
                                        LogUtils.e("日期：" + articleDate);
                                        switch (articleSuperChapter) {
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
                                    if (intervalDays <= 31) {
                                        //获取一周内的文章
                                        LogUtils.e("日期：" + articleDate);
                                        switch (articleSuperChapter) {
                                            case Constants.UI:
                                                mUIMonth ++;
                                                break;
                                            case Constants.JNI:
                                                mJNIMonth ++;
                                                break;
                                            case Constants.COMPONENTS:
                                                mComponentsMonth ++;
                                                break;
                                            case Constants.COMM_CTRLS:
                                                mCommCtrlsMonth ++;
                                                break;
                                            case Constants.CTRLS:
                                                mCtrlsMonth ++;
                                                break;
                                            case Constants.PROJECTS:
                                                mProjectsMonth ++;
                                                break;
                                            case Constants.DATA:
                                                mDataMonth ++;
                                                break;
                                            case Constants.HARDWARE:
                                                mHardMonth ++;
                                                break;
                                            case Constants.KNOWLEDAGE:
                                                mKnowledgeMonth ++;
                                                break;
                                            case Constants.IMAGE:
                                                mImageMonth ++;
                                                break;
                                            case Constants.PLATFORMS:
                                                mPlatformsMonth ++;
                                                break;
                                            case Constants.KOTLIN:
                                                mKotlinMonth ++;
                                                break;
                                            case Constants.JETPACK:
                                                mJetpackMonth ++;
                                                break;
                                            case Constants.ANIM:
                                                mAnimMonth ++;
                                                break;
                                            case Constants.FRAMEWORK:
                                                mFrameworkMonth ++;
                                                break;
                                            case Constants.JAVA:
                                                mJavaMonth ++;
                                                break;
                                            case Constants.MEDIA:
                                                mMediaMonth ++;
                                                break;
                                            case Constants.NET:
                                                mNetMonth ++;
                                                break;
                                            case Constants.DEV:
                                                mDevMonth ++;
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                } else {
                                    LogUtils.e("标题：" + articleTitle + "，体系：" + articleChapter + "，模块：" + articleSuperChapter);
                                    switch (articleSuperChapter) {
                                        case Constants.UI:
                                            mUI++;
                                            mUIMonth++;
                                            break;
                                        case Constants.JNI:
                                            mJNI++;
                                            mJNIMonth++;
                                            break;
                                        case Constants.COMPONENTS:
                                            mComponents++;
                                            mComponentsMonth++;
                                            break;
                                        case Constants.COMM_CTRLS:
                                            mCommCtrls++;
                                            mCommCtrlsMonth++;
                                            break;
                                        case Constants.CTRLS:
                                            mCtrls++;
                                            mCtrlsMonth++;
                                            break;
                                        case Constants.PROJECTS:
                                            mProjects++;
                                            mProjectsMonth++;
                                            break;
                                        case Constants.DATA:
                                            mData++;
                                            mDataMonth++;
                                            break;
                                        case Constants.HARDWARE:
                                            mHard++;
                                            mHardMonth++;
                                            break;
                                        case Constants.KNOWLEDAGE:
                                            mKnowledge++;
                                            mKnowledgeMonth++;
                                            break;
                                        case Constants.IMAGE:
                                            mImage++;
                                            mImageMonth++;
                                            break;
                                        case Constants.PLATFORMS:
                                            mPlatforms++;
                                            mPlatformsMonth++;
                                            break;
                                        case Constants.KOTLIN:
                                            mKotlin++;
                                            mKotlinMonth++;
                                            break;
                                        case Constants.JETPACK:
                                            mJetpack++;
                                            mJetpackMonth++;
                                            break;
                                        case Constants.ANIM:
                                            mAnim++;
                                            mAnimMonth++;
                                            break;
                                        case Constants.FRAMEWORK:
                                            mFramework++;
                                            mFrameworkMonth++;
                                            break;
                                        case Constants.JAVA:
                                            mJava++;
                                            mJavaMonth++;
                                            break;
                                        case Constants.MEDIA:
                                            mMedia++;
                                            mMediaMonth++;
                                            break;
                                        case Constants.NET:
                                            mNet++;
                                            mNetMonth++;
                                            break;
                                        case Constants.DEV:
                                            mDev++;
                                            mDevMonth++;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }

                            //将文章数量存储到数据库中
                            ArticleNumBean articleNumBean = new ArticleNumBean();
                            articleNumBean.setUI(mUI);
                            articleNumBean.setJNI(mJNI);
                            articleNumBean.setComponents(mComponents);
                            articleNumBean.setCtrls(mCtrls);
                            articleNumBean.setProjects(mProjects);
                            articleNumBean.setData(mData);
                            articleNumBean.setHard(mHard);
                            articleNumBean.setKnowledge(mKnowledge);
                            articleNumBean.setImage(mImage);
                            articleNumBean.setPlatforms(mPlatforms);
                            articleNumBean.setKotlin(mKotlin);
                            articleNumBean.setJetpack(mJetpack);
                            articleNumBean.setAnim(mAnim);
                            articleNumBean.setFramework(mFramework);
                            articleNumBean.setJava(mJava);
                            articleNumBean.setMedia(mMedia);
                            articleNumBean.setNet(mNet);
                            articleNumBean.setDev(mDev);
                            articleNumBean.save();

                            articleNumBean.setUIMonth(mUIMonth);
                            articleNumBean.setJNIMonth(mJNIMonth);
                            articleNumBean.setComponentsMonth(mComponentsMonth);
                            articleNumBean.setCtrlsMonth(mCtrlsMonth);
                            articleNumBean.setProjectsMonth(mProjectsMonth);
                            articleNumBean.setDataMonth(mDataMonth);
                            articleNumBean.setHardMonth(mHardMonth);
                            articleNumBean.setKnowledgeMonth(mKnowledgeMonth);
                            articleNumBean.setImageMonth(mImageMonth);
                            articleNumBean.setPlatformsMonth(mPlatformsMonth);
                            articleNumBean.setKotlinMonth(mKotlinMonth);
                            articleNumBean.setJetpackMonth(mJetpackMonth);
                            articleNumBean.setAnimMonth(mAnimMonth);
                            articleNumBean.setFrameworkMonth(mFrameworkMonth);
                            articleNumBean.setJavaMonth(mJavaMonth);
                            articleNumBean.setMediaMonth(mMediaMonth);
                            articleNumBean.setNetMonth(mNetMonth);
                            articleNumBean.setDevMonth(mDevMonth);
                            articleNumBean.save();
                        }
                    });
        }

    }
}
