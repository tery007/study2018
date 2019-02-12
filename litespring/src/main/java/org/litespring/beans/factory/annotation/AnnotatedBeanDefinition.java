package org.litespring.beans.factory.annotation;

import org.litespring.beans.factory.BeanDefinition;
import org.litespring.core.io.type.AnnotationMetaData;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetaData getAnnotationMetaData();

}
