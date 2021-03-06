package org.litespring.test.v1;

import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreExceptioin;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.PetStoreService;

import static org.junit.Assert.*;

/**
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public class BeanFactoryTest {

	DefaultBeanFactory factory = null;
	XmlBeanDefinitionReader reder = null;

	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		reder = new XmlBeanDefinitionReader(factory);

	}

	@Test
	public void testGetBean() {
		try {
			reder.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));

			BeanDefinition bd = factory.getBeanDefinition("petStore");

			assertTrue(bd.isSingleton());

			assertFalse(bd.isPrototype());

			assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

			assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());

			PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
			System.out.println(petStore.a);
		} catch (BeanCreationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidBean() {
		reder.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
		try {
			factory.getBean("invalidBean");
		} catch (BeanCreationException e) {
			e.printStackTrace();
		}
		//Assert.fail("expect BeanCreationException");
	}

	@Test
	public void testInvalidXML() {
		try {
			reder.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
		} catch (BeanDefinitionStoreExceptioin e) {
			e.printStackTrace();
		}
	}

}
