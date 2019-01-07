package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.BeanDefinitionValueResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.dao.v2.GoodDao;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2019/1/1 上午11:49
 **/
public class BeanDefinitionValueResolverTest {

    @Test
    public void testBeanDefinitionValue() {

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("goodService-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference reference = new RuntimeBeanReference("goodDao");
        Object val = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(val);
        Assert.assertTrue(val instanceof GoodDao);

    }
}
