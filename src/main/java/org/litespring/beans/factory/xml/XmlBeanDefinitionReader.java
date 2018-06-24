package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;

import org.litespring.beans.factory.BeanDefinitionStoreExceptioin;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 专门用于读取xml文件
 * @Author: lzy
 * @Date: Created in 11:10 2018/6/24
 */
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(String configFile) {
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
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
                this.registry.registerBeanDefinition(id, bd);
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
}
