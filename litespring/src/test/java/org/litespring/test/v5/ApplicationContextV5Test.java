package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.context.support.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v5.GoodService;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/15
 **/
public class ApplicationContextV5Test {

    @Before
    public void setUp() {
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceOrder() {
        ApplicationContext context = new ClassPathXmlApplicationContext("goodService-v5.xml");
        GoodService service = (GoodService) context.getBean("goodService");

        Assert.assertNotNull(service.getAccountDao());
        Assert.assertNotNull(service.getItemDao());

        service.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals(MessageTracker.START_MSG, msgs.get(0));
        Assert.assertEquals(MessageTracker.PLACE_ORDER, msgs.get(1));
        Assert.assertEquals(MessageTracker.COMMIT_MSG, msgs.get(2));
    }
}
