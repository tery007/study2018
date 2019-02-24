package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.config.MethodLocatingFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.tx.TransactionManager;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public class MethodLocatingFactoryTest {

    @Test
    public void testLocate() throws NoSuchMethodException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("goodService-v5.xml"));

        MethodLocatingFactory locatingFactory = new MethodLocatingFactory();
        locatingFactory.setTargetBeanName("tx");
        locatingFactory.setMethodName("start");
        locatingFactory.setBeanFactory(factory);

        Method method = locatingFactory.getObject();
        Assert.assertEquals(method.getDeclaringClass(), TransactionManager.class);
        Assert.assertEquals(TransactionManager.class.getMethod("start"),method);

    }


}
