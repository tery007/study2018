package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.support.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.GoodService;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午3:15
 **/
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("goodService-v1.xml");
        GoodService petStoreServic = (GoodService) applicationContext.getBean("goodService");
        Assert.assertNotNull(petStoreServic);

    }
}
