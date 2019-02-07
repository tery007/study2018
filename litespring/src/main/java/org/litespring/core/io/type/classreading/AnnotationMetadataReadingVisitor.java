package org.litespring.core.io.type.classreading;

import org.litespring.core.annotion.AnnotationAttributes;
import org.litespring.core.io.type.AnnotationMetaData;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public class AnnotationMetadataReadingVisitor extends ClassPathMetaDataReadingVisitor implements AnnotationMetaData {

    private final Set<String> annotationSet = new LinkedHashSet<>(4);
    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>(4);
    @Override
    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    @Override
    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    @Override
    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return attributesMap.get(annotationType);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, attributesMap);
    }

}
