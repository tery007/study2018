package org.litespring.beans.factory.xml;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午5:04
 **/
public class XmlBeanDefinitionReader {

    public static final Logger logger = Logger.getLogger(XmlBeanDefinitionReader.class);

    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRBUTE = "value";
    private BeanDefinitionRegistry register;
    public XmlBeanDefinitionReader(BeanDefinitionRegistry register) {
        this.register = register;
    }


    /**
     * 加载resource资源，解析xml文件，
     * 将beanId和class构成的BeanDefinition注册到BeanDefinitionRegistry中
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
                parsePropertyElement(ele, bd);
                register.registerBeanDefinition(beanId, bd);
            });

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        }finally {
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
     * 解析<property></property>标签元素
     *
     * @param ele
     * @param bd
     */
    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        List<Element> elements = ele.elements();
        elements.stream().forEach(e -> {
            String propertyName = e.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.error("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(e, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            bd.getPropertyValues().add(pv);
        });
    }

    private Object parsePropertyValue(Element e, String propertyName) {
        boolean hasRef = e.attribute(REF_ATTRIBUTE) != null;
        boolean hasValue = e.attribute(VALUE_ATTRBUTE) != null;
        if (hasRef) {
            if (!StringUtils.hasText(e.attributeValue(REF_ATTRIBUTE))) {
                logger.error(propertyName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference reference = new RuntimeBeanReference(e.attributeValue(REF_ATTRIBUTE));
            return reference;
        }
        if (hasValue) {
            TypedStringValue stringValue = new TypedStringValue(e.attributeValue(VALUE_ATTRBUTE));
            return stringValue;
        }
        throw new RuntimeException(propertyName + " must specify a ref or value");
    }
}
