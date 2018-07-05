package org.litespring.beans;

/**
 * 定义bean工厂
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public interface BeanDefinition {

	public static final String SCOPE_SINGLETON = "singleton";

	public static final String SCOPE_PROTOTYPE = "prototype";

	public static final String SCOPE_DEFAULT = "";

	boolean isSingleton();

	boolean isPrototype();

	String getScope();

	void setScope(String scope);

	/**
	 * 获取bean对象的class属性的值
	 * @return
	 */
	String getBeanClassName();

}
