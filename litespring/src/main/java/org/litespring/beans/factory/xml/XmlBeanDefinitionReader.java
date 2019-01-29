package org.litespring.beans.factory.xml;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午5:04
 **/
public class XmlBeanDefinitionReader {

    public static final Logger logger = Logger.getLogger(XmlBeanDefinitionReader.class);

    public static final String ID_ATTRIBUTE            = "id";
    public static final String CLASS_ATTRIBUTE         = "class";
    public static final String SCOPE_ATTRIBUTE         = "scope";
    public static final String PROPERTY_ELEMENT        = "property";
    public static final String NAME_ATTRIBUTE          = "name";
    public static final String REF_ATTRIBUTE           = "ref";
    public static final String VALUE_ATTRIBUTE         = "value";
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    public static final String TYPE_ATTRIBUTE          = "type";

    private BeanDefinitionRegistry register;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry register) {
        this.register = register;
    }


    /**
     * 加载resource资源，解析xml文件，
     * 将beanId和class构成的BeanDefinition注册到BeanDefinitionRegistry中
     *
     * @param resource
     * @throws
     */
    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            elements.stream().forEach(ele -> {
                String beanId = ele.attributeValue(ID_ATTRIBUTE);
                String clazz = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(beanId, clazz);
                if (ele.attribute(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                parseConstructorArgElements(ele, bd);
                parsePropertyElement(ele, bd);
                register.registerBeanDefinition(beanId, bd);
            });

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解析<constructor-arg/>标签元素
     *
     * @param ele
     * @param bd
     */
    private void parseConstructorArgElements(Element ele, BeanDefinition bd) {
        Iterator iter = ele.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iter.hasNext()) {
            Element consElement = (Element) iter.next();
            parseConstructorArgElement(consElement, bd);

        }
    }

    private void parseConstructorArgElement(Element consElement, BeanDefinition bd) {
        String name = consElement.attributeValue(NAME_ATTRIBUTE);
        String type = consElement.attributeValue(TYPE_ATTRIBUTE);
        Object value = parsePropertyValue(consElement, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasText(name)) {
            valueHolder.setName(name);
        }
        if (StringUtils.hasText(type)) {
            valueHolder.setType(type);
        }
        bd.getConstructorArgument().addArgumentValueHolder(valueHolder);
    }

    /**
     * 解析<property></property>标签元素
     *
     * @param ele
     * @param bd
     */
    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        Iterator iter = ele.elementIterator(PROPERTY_ELEMENT);
        while (iter.hasNext()) {
            Element proElement = (Element) iter.next();
            String propertyName = proElement.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.error("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(proElement, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            bd.getPropertyValues().add(pv);
        }
    }

    private Object parsePropertyValue(Element ele, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";


        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE)!=null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) !=null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        }
        else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
