package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;
import java.util.Objects;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/29 上午11:22
 **/
public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("goodService-v2.xml"));
        BeanDefinition beanDefinition = factory.getBeanDefinition("goodService");
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        Assert.assertTrue(propertyValues.size() == 4);
        {
            PropertyValue propertyValue = this.getPropertyValue("goodDao", propertyValues);
            Assert.assertNotNull(propertyValue);
            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue propertyValue = this.getPropertyValue("debitDao", propertyValues);
            Assert.assertNotNull(propertyValue);
            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> propertyValues) {
        return propertyValues.stream().filter(a -> Objects.equals(name, a.getName())).findAny().get();
    }
}
