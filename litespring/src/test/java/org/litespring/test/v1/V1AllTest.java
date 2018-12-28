package org.litespring.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午2:52
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTest.class,
        ResourceTest.class
})
public class V1AllTest {
}
