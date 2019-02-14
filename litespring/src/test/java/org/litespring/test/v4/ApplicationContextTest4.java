package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.support.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v4.GoodService;

public class ApplicationContextTest4 {

	@Test
	public void testGetBeanProperty() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("goodService-v4.xml");
		GoodService petStore = (GoodService) ctx.getBean("goodService");
		
		Assert.assertNotNull(petStore.getAccountDao());
		Assert.assertNotNull(petStore.getItemDao());
		
	}	
}
