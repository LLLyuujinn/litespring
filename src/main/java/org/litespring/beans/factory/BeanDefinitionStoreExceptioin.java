package org.litespring.beans.factory;

import org.litespring.beans.BeanException;

/**
 * @Author: lzy
 * @Date: Created in 21:28 2018/6/13
 */
public class BeanDefinitionStoreExceptioin extends BeanException {

    public BeanDefinitionStoreExceptioin(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreExceptioin(String msg, Throwable cause) {
        super(msg, cause);
    }
}
