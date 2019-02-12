package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.litespring.context.annotation.ScannedGenericBeanDefinition;
import org.litespring.core.annotion.AnnotationAttributes;
import org.litespring.core.io.type.AnnotationMetaData;
import org.litespring.stereotype.Component;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScannerTest() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "org.litespring.dao.v4,org.litespring.service.v4";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);
        String annotation = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("goodService");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetaData mt = sbd.getAnnotationMetaData();
            Assert.assertTrue(mt.hasAnnotation(annotation));
            AnnotationAttributes attributes = mt.getAnnotationAttributes(annotation);
            Assert.assertEquals(attributes.getString("value"), "goodService");

        }
        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetaData mt = sbd.getAnnotationMetaData();
            Assert.assertTrue(mt.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetaData mt = sbd.getAnnotationMetaData();
            Assert.assertTrue(mt.hasAnnotation(annotation));
        }
    }
}
