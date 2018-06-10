package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * 抽象工厂模式，定义各个工厂的行为
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public interface BeanFactory {

	/**
	 * 获取一个bean工厂
	 * @param beanId
	 * @return
	 */
	BeanDefinition getBeanDefinition(String beanId);

	/**
	 * 获取这个bean的对象实例
	 * @param beanId
	 * @return
	 */
	Object getBean(String beanId);

}
