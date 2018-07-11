package com.zhy.test.v1;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.factory.BeanCreationException;
import com.zhy.beans.factory.BeanDefinitionStoreException;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertEquals("com.zhy.service.v1.PetStoreService", bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(petStore);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        try {
            factory.getBean("invalidBean");
        }catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");

    }

    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinitions("XXX.xml");
        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");

    }


}
