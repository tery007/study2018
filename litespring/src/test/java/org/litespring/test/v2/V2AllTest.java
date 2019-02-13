package org.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/29 上午11:10
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class,CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,TypeConverterTest.class
})
public class V2AllTest {
}
