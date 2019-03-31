package com.hurley.codehub.bean.wanandroid;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/2/26 1:18 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 实体类基类（网络请求返回的代码）
 * </pre>
 */
public class BaseBean<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    /**
     * 服务器返回的错误码
     * 0：成功，1：失败
     */
    public int errorCode;

    /**
     * 服务器返回的成功或失败的提示
     */
    public String errorMsg;

    /**
     * 服务器返回的数据
     */
    public T data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
