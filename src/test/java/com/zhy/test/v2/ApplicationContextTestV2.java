package com.zhy.test.v2;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.PropertyValue;
import com.zhy.beans.factory.config.RuntimeBeanReference;
import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.context.ApplicationContext;
import com.zhy.context.support.ClassPathXmlApplicationContext;
import com.zhy.core.io.ClassPathResource;
import com.zhy.service.v2.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petStore-v2.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }



}
