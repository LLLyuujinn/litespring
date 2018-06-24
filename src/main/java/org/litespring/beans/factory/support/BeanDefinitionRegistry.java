package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * @Author: lzy
 * @Date: Created in 11:18 2018/6/24
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId, BeanDefinition bd);
}
