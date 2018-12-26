package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午11:23
 **/
public class BeanFactoryTest {

    @Test
    public void testBeanFactory() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        BeanDefinition definition = beanFactory.getBeanDefinition("petStore");
        Assert.assertEquals(definition.getBeanClassName(), "org.litespring.service.v1.PetStoreService");
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
