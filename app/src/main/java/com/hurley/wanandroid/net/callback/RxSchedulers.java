package com.hurley.wanandroid.net.callback;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 5:36 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 通用的Rx线程转换类
 * </pre>
 */
public class RxSchedulers {

    //🔥subscribeOn操作符用于指定Observable自身在哪个线程上运行
    //🔥如果Observable需要执行耗时操作，一般可以让其在新开的一个子线程上运行。
    //🔥observerOn用来指定Observer所运行的线程，也就是发射出的数据在哪个线程上使用。
    //🔥一般情况下会指定在主线程中运行，因为可以在主线程进行UI更新操作。

    static final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            //I/O操作（读写文件、读写数据库、网络信息交互等）所使用的Scheduler。
            //🔥行为模式和newThread()差不多，区别在于io()的内部实现是用一个无数量上限的线程池，可以重用空闲的线程。
            //因此，多数情况下io()比newThread()更有效率。
            return (upstream).subscribeOn(Schedulers.io())
                    //表示运行在主线程
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }
}
