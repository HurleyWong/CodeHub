package com.hurley.wanandroid.utils;

import com.blankj.utilcode.util.NetworkUtils;

import com.facebook.stetho.common.LogUtil;
import com.hurley.wanandroid.base.BaseBean;
import com.hurley.wanandroid.bean.PageBean;
import com.hurley.wanandroid.bean.UserBean;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/2 3:43 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : Rx封装工具类
 * </pre>
 */
public class RxUtils {

    static final ObservableTransformer schedulersTransformer = upstream -> (upstream).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    /**
     * 封装Rx线程相关操作
     * @param <T>           指定的泛型类型
     * @return              FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFTSchedulerHelper() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 封装Rx线程切换相关操作
     * 后台线程获取数据，主线程显式
     * Schedulers.io()：I/O操作（读写文件、读写数据库、网络信息交互等）所使用的Scheduler
     * Schedulers.mainThread()：指定回调操作将在Android主线程中运行
     * @param <T>           指定的泛型类型
     * @return              ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseBean<T>, BaseBean<T>> rxScheduleHelper() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 封装api请求后统一处理
     * flatMap()把传入的参数转化之后返回另一个对象
     * flatMap()返回的是一Observable对象，并且这个Observable对象并不是直接被发送到Subscriber的回调方法中
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> handleResult() {
        LogUtil.e("Rx2");
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseBean<T>, Observable<T>>) baseBean -> {
            LogUtil.e("???");
            if (baseBean.getErrorCode() == BaseBean.SUCCESS
                    && baseBean.getData() != null
                    && NetworkUtils.isConnected()) {
                LogUtil.e("Rx3");
                return createData(baseBean.getData());
            } else {
                LogUtil.e("Rx4");
                return Observable.error(new Exception());
            }
        });
    }

    /**
     * 退出登录返回结果处理
     * @param <T>           指定的泛型类型
     * @return              ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> handleLogoutResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseBean<T>, Observable<T>>) baseBean -> {
                    if (baseBean.getErrorCode() == BaseBean.SUCCESS
                            && NetworkUtils.isConnected()) {
                        //创建一个非空数据源，避免onNext()传入null
                        return createData(StringUtils.cast(new UserBean()));
                    } else {
                        return Observable.error(new Exception());
                    }
                });
    }

    /**
     * 收藏返回结果处理
     * @param <T>           指定的泛型类型
     * @return              ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> handleCollectResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseBean<T>, Observable<T>>) baseBean -> {
                    if (baseBean.getErrorCode() == BaseBean.SUCCESS
                            && NetworkUtils.isConnected()) {
                        //创建一个非空数据源，避免onNext()传入null
                        return createData(StringUtils.cast(new PageBean<T>()));
                    } else {
                        return Observable.error(new Exception());

                    }
                });
    }

    /**
     * 获得Observable
     * @param t
     * @param <T>           指定的泛型类型
     * @return
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

}
