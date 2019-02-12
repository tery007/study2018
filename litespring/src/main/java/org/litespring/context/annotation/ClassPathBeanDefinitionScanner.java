package org.litespring.context.annotation;

import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.BeanNameGenerator;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;
import org.litespring.core.io.type.AnnotationMetaData;
import org.litespring.core.io.type.classreading.MetaDataReader;
import org.litespring.core.io.type.classreading.SimpleMetaDataReader;
import org.litespring.stereotype.Component;
import org.litespring.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public class ClassPathBeanDefinitionScanner {

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();


    public Set<BeanDefinition> doScan(String packagesToScan) {
        String[] packages = StringUtils.tokenizeToStringArray(packagesToScan, ",");

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>(4);
        for (String basePackage : packages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getID(), candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {

        Set<BeanDefinition> candidates = new LinkedHashSet<>(4);
        Resource[] resources = resourceLoader.getResources(basePackage);

        for (Resource resource : resources) {
            try {
                MetaDataReader reader = new SimpleMetaDataReader(resource);
                AnnotationMetaData metaData = reader.getAnnotationMetadata();
                if (metaData.hasAnnotation(Component.class.getName())) {
                    ScannedGenericBeanDefinition bd = new ScannedGenericBeanDefinition(metaData);
                    String beanId = beanNameGenerator.generateBeanName(bd, registry);
                    bd.setBeanId(beanId);
                    candidates.add(bd);
                }
            } catch (Throwable ex) {
                throw new BeanDefinitionStoreException(
                        "Failed to read candidate component class: " + resource, ex);
            }
        }
        return candidates;
    }
}
