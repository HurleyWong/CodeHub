package com.hurley.wanandroid.net.exception;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019/3/3 12:31 PM
 *      github  : https://github.com/HurleyJames
 *      desc    : 服务器异常类
 * </pre>
 */
public class ServerException extends Exception {

    private int code;

    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
