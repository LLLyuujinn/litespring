package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public class DefaultBeanFactory implements BeanFactory,BeanDefinitionRegistry {

	//用于存放bean对象的map
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	//构造方法
	public DefaultBeanFactory() {

	}

	//将bean对象添加到map中
	public void registerBeanDefinition(String beanId, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanId, bd);
	}

	/**
	 * 根据id获取一个BeanDefinition实例（bean对象）
	 * @param beanId <bean>标签中的id
	 * @return bean工厂实例
	 */
	public BeanDefinition getBeanDefinition(String beanId) {
		return this.beanDefinitionMap.get(beanId);
	}

	/**
	 * 通过id获取对象实例
	 * @param beanId <bean>标签中的id
	 * @return 对象实例
	 */
	public Object getBean(String beanId) {
		BeanDefinition bd = this.getBeanDefinition(beanId);
		if (bd == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getBeanClassName();
		try {
			Class<?> clazz = cl.loadClass(beanClassName);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
		}
	}

}
