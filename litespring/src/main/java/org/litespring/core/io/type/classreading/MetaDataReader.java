package org.litespring.core.io.type.classreading;

import org.litespring.core.io.Resource;
import org.litespring.core.io.type.AnnotationMetaData;
import org.litespring.core.io.type.ClassMetadata;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/7
 **/
public interface MetaDataReader {

    /**
     * Return the resource reference for the class file.
     */
    Resource getResource();

    /**
     * Read basic class metadata for the underlying class.
     */
    ClassMetadata getClassMetadata();

    /**
     * Read full annotation metadata for the underlying class,
     * including metadata for annotated methods.
     */
    AnnotationMetaData getAnnotationMetadata();
}
