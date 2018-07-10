package com.zhy.test.v1;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.factory.BeanFactory;
import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertEquals("com.zhy.service.v1.PetStoreService", bd.getBeanClassName());

        PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
        Assert.assertNotNull(petStore);
    }
}
