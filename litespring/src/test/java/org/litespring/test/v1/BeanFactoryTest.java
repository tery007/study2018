package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.PetStoreService;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/25 下午11:23
 **/
public class BeanFactoryTest {

    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;
    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }
    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        Assert.assertTrue(definition.isSingleton());

        Assert.assertTrue(definition.isPrototype());

        Assert.assertEquals(definition.getBeanClassName(), "org.litespring.service.v1.PetStoreService");

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        Assert.assertNotNull(petStoreService);
    }
}
