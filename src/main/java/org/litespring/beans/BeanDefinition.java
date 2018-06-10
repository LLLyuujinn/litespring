package org.litespring.beans;

/**
 * 定义bean工厂
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public interface BeanDefinition {

	/**
	 * 获取bean对象的class属性的值
	 * @return
	 */
	String getBeanClassName();

}
