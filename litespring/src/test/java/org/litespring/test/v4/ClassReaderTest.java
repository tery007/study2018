package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotion.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.type.classreading.AnnotationMetadataReadingVisitor;
import org.litespring.core.io.type.classreading.ClassPathMetaDataReadingVisitor;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public class ClassReaderTest {

    @Test
    public void testGetMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/GoodService.class");
        ClassReader classReader = new ClassReader(resource.getInputStream());
        ClassPathMetaDataReadingVisitor visitor = new ClassPathMetaDataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("org.litespring.service.v4.GoodService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnotation() throws IOException {

        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/GoodService.class");
        ClassReader classReader = new ClassReader(resource.getInputStream());
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        String annotation = "org.litespring.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        Assert.assertEquals("goodService", attributes.get("value"));
    }
}
