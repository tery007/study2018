package org.litespring.core.io.type.classreading;

import org.litespring.core.io.Resource;
import org.litespring.core.io.type.AnnotationMetaData;
import org.litespring.core.io.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public class SimpleMetaDataReader implements MetaDataReader {

    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetaData annotationMetadata;

    public SimpleMetaDataReader(Resource resource) throws Exception {
        InputStream is = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;

        try {
            classReader = new ClassReader(is);
        } finally {
            is.close();
        }

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public AnnotationMetaData getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
