package com.hurley.codehub.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/14 3:42 PM
 *      github  : https://github.com/HurleyJames
 *      desc    :
 * </pre>
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
