package org.litespring.core.io.type;

import org.litespring.core.annotion.AnnotationAttributes;

import java.util.Set;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public interface AnnotationMetaData extends ClassMetadata {

    Set<String> getAnnotationTypes();
    boolean hasAnnotation(String annotationType);
    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
