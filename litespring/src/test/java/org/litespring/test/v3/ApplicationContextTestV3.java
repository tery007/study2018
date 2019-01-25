package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.support.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.GoodService;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/16 下午10:52
 **/
public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("goodService-v3.xml");
        GoodService service = (GoodService) context.getBean("goodService");
        Assert.assertNotNull(service.getAccountDao());
        Assert.assertNotNull(service.getItemDao());
        Assert.assertEquals(1, service.getVersion());
    }
}
