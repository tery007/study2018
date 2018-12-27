package org.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午5:04
 **/
public class XmlBeanDefinitionReader {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry register;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry register) {
        this.register = register;
    }


    public void loadBeanDefinitions(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            elements.stream().forEach(element -> {
                String beanId = element.attributeValue(ID_ATTRIBUTE);
                String clazz = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanId, clazz);
                register.registerBeanDefinition(beanId, beanDefinition);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
