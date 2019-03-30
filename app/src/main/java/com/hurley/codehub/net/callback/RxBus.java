package com.hurley.codehub.net.callback;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/15 5:23 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 事件总线类，代替EventBus和otto
 * </pre>
 */
public class RxBus {
    private static volatile RxBus rxBus;

    /**
     * 主题
     */
    private final FlowableProcessor<Object> mBus;

    /**
     * 🔥PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     * 🔥RxJava的Subject有 PublishSubject | BehaviorSubject | ReplaySubject | AsyncSubject
     */
    public RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    /**
     * 获得RxBus的实例
     * 单例模式的双重检查模式
     * @return
     */
    public static RxBus getInstance() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    /**
     * 发送事件
     * @param o
     */
    public void post(Object o) {
        mBus.onNext(o);
    }

    /**
     * 接受事件
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}
