package com.hurley.codehub.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:44 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLifeCycle {
    String value() default "App";
}
