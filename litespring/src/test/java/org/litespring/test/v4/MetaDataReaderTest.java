package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotion.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.type.AnnotationMetaData;
import org.litespring.core.io.type.classreading.MetaDataReader;
import org.litespring.core.io.type.classreading.SimpleMetaDataReader;
import org.litespring.stereotype.Component;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public class MetaDataReaderTest {

    @Test
    public void testGetMetadata() throws Exception {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/GoodService.class");
        MetaDataReader reader = new SimpleMetaDataReader(resource);
        AnnotationMetaData mataData = reader.getAnnotationMetadata();
        String annotation = Component.class.getName();
        Assert.assertTrue(mataData.hasAnnotation(annotation));
        AnnotationAttributes attributes = mataData.getAnnotationAttributes(annotation);
        Assert.assertEquals("goodService", attributes.get("value"));
        Assert.assertEquals("org.litespring.service.v4.GoodService", mataData.getClassName());
    }
}
