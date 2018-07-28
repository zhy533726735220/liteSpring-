package com.zhy.test.v4;

import com.zhy.context.ApplicationContext;
import com.zhy.context.support.ClassPathXmlApplicationContext;
import com.zhy.service.v4.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest4 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petStore-v4.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }
}
