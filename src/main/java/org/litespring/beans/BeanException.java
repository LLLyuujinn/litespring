package org.litespring.beans;

/**
 * @Author: lzy
 * @Date: Created in 21:24 2018/6/13
 */
public class BeanException extends RuntimeException{

    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
