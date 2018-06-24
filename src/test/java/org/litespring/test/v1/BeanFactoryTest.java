package org.litespring.test.v1;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreExceptioin;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

/**
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		try {
			BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
			BeanDefinition bd = factory.getBeanDefinition("petStore");

			assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());

			PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
			System.out.println(petStore.a);
		} catch (BeanCreationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			e.printStackTrace();
		}
		Assert.fail("expect BeanCreationException");
	}

	@Test
	public void testInvalidXML() {
		try {
			new DefaultBeanFactory("xxx.xml");
		} catch (BeanDefinitionStoreExceptioin e) {
			e.printStackTrace();
		}
	}

}
