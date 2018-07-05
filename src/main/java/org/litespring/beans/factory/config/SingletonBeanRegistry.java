package org.litespring.beans.factory.config;

public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletObject);

    Object getSingleton(String beanName);
}
