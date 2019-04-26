package com.hurley.codehub.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date   : 2019/2/20 上午11:06
 *      github : https://github.com/HurleyJames
 *      desc   : Intent数据存取工具类
 * </pre>
 */
public final class IntentUtils {

    private static final String TAG = "IntentUtil";

    private static Class<?> sCurrentClass;

    private static IntentUtils sInstance;

    private static HashMap<String, Object> sMap;

    private IntentUtils() {

    }

    public static IntentUtils getInstance(Class<? extends Activity> cls) {
        if (sInstance == null) {
            sInstance = new IntentUtils();
        }
        if (sMap == null) {
            sMap = new HashMap<>();
        }
        sCurrentClass = cls;
        return sInstance;
    }

    /**
     * 跳转到Activity
     * @param context       context对象
     */
    public void startActivity(Context context) {
        startActivity(context, false);
    }

    /**
     * 跳转到activity后再销毁当前Activity
     * @param activity
     */
    public void startActivityFinish(Activity activity) {
        startActivity(activity, false);
        activity.finish();
    }

    /**
     * 跳转到Activity
     * @param context       context对象
     * @param newTask       是否开启新的任务栈
     */
    public void startActivity(Context context, boolean newTask) {
        Intent intent = new Intent(context, sCurrentClass);
        if (newTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到Activity
     * @param activity      activity对象
     * @param requestCode   请求码
     */
    public void startActivity(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, sCurrentClass), requestCode);
    }

    /**
     * 设置结果码
     * @param activity      activity对象
     * @param resultCode    结果码
     */
    public void setResult(Activity activity, int resultCode) {
        activity.setResult(resultCode);
    }

    /**
     * 销毁Activity
     * @param activity      activity对象
     */
    public void finish(Activity activity) {
        activity.finish();
    }

    //Object

    public IntentUtils put(Class<?> clazz, Object object) {
        return put(sCurrentClass + clazz.getName(), object);
    }

    public IntentUtils put(String key, Object object) {
        sMap.put(key, object);
        return this;
    }

    public <T extends Object> T get(Class<T> clazz) {
        return get(sCurrentClass + clazz.getName());
    }

    public <T extends Object> T get(String key) {
        T t = (T) sMap.get(key);
        //移除这个对象，避免内存泄露
        sMap.remove(key);
        return t;
    }

    //String

    public IntentUtils putString(String s) {
        return put(String.class, s);
    }

    public IntentUtils putString(String key, String s) {
        return put(key, s);
    }

    public String getString() {
        return get(String.class);
    }

    public String getString(String key) {
        return get(key);
    }

    //Integer

    public IntentUtils putInteger(Integer i) {
        return put(Integer.class, i);
    }

    public IntentUtils putInteger(String key, Integer i) {
        return put(key, i);
    }

    public Integer getInteger() {
        return get(Integer.class);
    }

    public Integer getInteger(String key) {
        return get(key);
    }

    //Long

    public IntentUtils putLong(Long l) {
        return put(Long.class, l);
    }

    public IntentUtils putLong(String key, Long l) {
        return put(key, l);
    }

    public Long getLong() {
        return get(Long.class);
    }

    public Long getLong(String key) {
        return get(key);
    }

    //Boolean

    public IntentUtils putBoolean(Boolean b) {
        return put(Boolean.class, b);
    }

    public IntentUtils putBoolean(String key, Boolean b) {
        return put(key, b);
    }

    public Boolean getBoolean() {
        return get(Boolean.class);
    }

    public Boolean getBoolean(String key) {
        return get(key);
    }

    //Double

    public IntentUtils putDouble(Double d) {
        return put(Double.class, d);
    }

    public IntentUtils putDouble(String key, Double d) {
        return put(key, d);
    }

    public Double getDouble() {
        return get(Double.class);
    }

    public Double getDouble(String key) {
        return get(key);
    }

    //Float

    public IntentUtils putFloat(Float f) {
        return put(Float.class, f);
    }

    public IntentUtils putFloat(String key, Float f) {
        return put(key, f);
    }

    public Float getFloat() {
        return get(Float.class);
    }

    public Float getFloat(String key) {
        return get(key);
    }

    //List

    public IntentUtils putList(List list) {
        return put(List.class, list);
    }

    public IntentUtils putList(String key, List list) {
        return put(key, list);
    }

    public List getList() {
        return get(List.class);
    }

    public List getList(String key) {
        return get(key);
    }
}


