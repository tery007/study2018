package org.litespring.core.io.type.classreading;

import org.litespring.core.annotion.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public final class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

    private String annotationType;
    private Map<String, AnnotationAttributes> attributesMap;
    AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public void visit(String attributeName, Object attributeVaule) {
        this.attributes.put(attributeName, attributeVaule);
    }

    @Override
    public void visitEnd() {
        this.attributesMap.put(this.annotationType, this.attributes);
    }
}
