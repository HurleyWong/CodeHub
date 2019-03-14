package com.hurley.wanandroid.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/5 3:43 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

    //@Scope注解，用来限定通过Module和Inject方式创建实例的生命周期能够与目标类的生命周期相同，起提醒和管理的作用。
}
