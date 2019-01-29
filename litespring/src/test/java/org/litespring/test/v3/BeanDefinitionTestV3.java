package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/21 下午11:40
 **/
public class BeanDefinitionTestV3 {

    @Test
    public void testBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("goodService-v3.xml"));
        BeanDefinition bd = factory.getBeanDefinition("goodService");
        Assert.assertEquals("org.litespring.service.v3.GoodService", bd.getBeanClassName());

        ConstructorArgument argument = bd.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> list = argument.getArgumentValues();
        Assert.assertEquals(3, list.size());
        RuntimeBeanReference rbr = (RuntimeBeanReference) list.get(0).getValue();
        Assert.assertEquals("accountDao", rbr.getBeanName());
        RuntimeBeanReference rbr2 = (RuntimeBeanReference) list.get(1).getValue();
        Assert.assertEquals("itemDao", rbr2.getBeanName());
        TypedStringValue tsv = (TypedStringValue) list.get(2).getValue();
        Assert.assertEquals("1",tsv.getValue());
    }
}
