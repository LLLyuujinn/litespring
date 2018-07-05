package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * 用于定义<bean>标签中的属性
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	private String beanClassName;
	private boolean singleton = true;
	private boolean prototype = false;
	private String scope = SCOPE_DEFAULT;

	/**
	 * 初始化
	 * @param id
	 * @param beanClassName
	 */
	public GenericBeanDefinition(String id, String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}

	public boolean isSingleton() {
		return this.singleton;
	}

	public boolean isPrototype() {
		return this.prototype;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	/**
	 * 获取bean对象的class属性的值
	 * @return
	 */
	public String getBeanClassName() {
		return this.beanClassName;
	}

}
