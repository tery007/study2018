package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.GoodService;

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
        reader.loadBeanDefinitions(new ClassPathResource("goodService-v1.xml"));

        BeanDefinition definition = factory.getBeanDefinition("goodService");

        Assert.assertTrue(definition.isSingleton());

        Assert.assertFalse(definition.isPrototype());

        Assert.assertEquals(definition.getBeanClassName(), "org.litespring.service.v1.GoodService");

        GoodService petStoreService = (GoodService) factory.getBean("goodService");

        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {

        reader.loadBeanDefinitions(new ClassPathResource("goodService-v1.xml"));
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXmlLoad() {
        try {
            reader.loadBeanDefinitions(new ClassPathResource("pettt.xml"));

        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
