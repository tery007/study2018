package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v4.AccountDao;
import org.litespring.service.v4.GoodService;

import java.lang.reflect.Field;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public class DependencyDescriptorTest {

    @Test
    public void testDependency() throws NoSuchFieldException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        Resource resource = new ClassPathResource("goodService-v4.xml");
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        Field f = GoodService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(f, true);
        Object obj = factory.resolveDependency(descriptor);

        Assert.assertTrue(obj instanceof AccountDao);
    }
}
