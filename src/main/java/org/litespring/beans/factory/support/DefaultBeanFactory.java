package org.litespring.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreExceptioin;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.utils.ClassUtils;

/**
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public class DefaultBeanFactory implements BeanFactory {

	//<bean>标签中的id属性
	public static final String ID_ATTRBUTE = "id";

	//<bean>标签中的class属性
	public static final String CLASS_ATTRIBUTE = "class";

	//用于存放bean对象的map
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	/**
	 * 根据配置文件路径去加载配置文件中的所有bean
	 * @param configFile
	 */
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinition(configFile);
	}

	/**
	 * 加载所有bean对象
	 * @param configFile
	 */
	private void loadBeanDefinition(String configFile) {
		InputStream is = null;
		try {
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement();//<bean>
			Iterator<Element> iter = root.elementIterator();
			while (iter.hasNext()) {
				Element ele = (Element) iter.next();
				String id = ele.attributeValue(ID_ATTRBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				this.beanDefinitionMap.put(id, bd);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreExceptioin("IOException parsing XML document");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据id获取一个BeanDefinition工厂实例（bean对象）
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
