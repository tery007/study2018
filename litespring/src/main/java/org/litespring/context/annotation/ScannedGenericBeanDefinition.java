package org.litespring.context.annotation;

import org.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.type.AnnotationMetaData;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetaData metaData;

    public ScannedGenericBeanDefinition(AnnotationMetaData metaData) {
        super();
        this.metaData = metaData;
        setBeanClassName(metaData.getClassName());
    }


    @Override
    public AnnotationMetaData getAnnotationMetaData() {
        return this.metaData;
    }
}
