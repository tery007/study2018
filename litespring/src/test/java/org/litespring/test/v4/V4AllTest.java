package org.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/13
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClassPathBeanDefinitionScannerTest.class, ClassReaderTest.class,
        DependencyDescriptorTest.class, MetaDataReaderTest.class,
        PackageResourceLoaderTest.class, XmlBeanDefinitionReaderTest.class
})
public class V4AllTest {
}
