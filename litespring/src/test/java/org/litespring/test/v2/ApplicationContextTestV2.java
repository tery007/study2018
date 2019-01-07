package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.support.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.dao.v2.DebitDao;
import org.litespring.dao.v2.GoodDao;
import org.litespring.service.v2.GoodService;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/29 上午10:36
 **/
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("goodService-v2.xml");
        GoodService goodService = (GoodService) context.getBean("goodService");
        Assert.assertNotNull(goodService.getDebitDao());
        Assert.assertNotNull(goodService.getGoodDao());

        Assert.assertTrue(goodService.getGoodDao() instanceof GoodDao);
        Assert.assertTrue(goodService.getDebitDao() instanceof DebitDao);

        Assert.assertEquals("wangjunkai", goodService.getOwner());
        Assert.assertEquals(2, goodService.getVersion());
    }
}
