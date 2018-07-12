package com.zhy.test.v1;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.factory.BeanCreationException;
import com.zhy.beans.factory.BeanDefinitionStoreException;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.core.io.ClassPathResource;
import com.zhy.core.io.Resource;
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
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertFalse(bd.isSingleton());
        Assert.assertFalse(bd.isPrototype());
        Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());

        Assert.assertEquals("com.zhy.service.v1.PetStoreService", bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(petStore);

        PetStoreService petStore1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertFalse(petStore.equals(petStore1));
    }
    @Test
    public void testGetSingletonBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("singletonPetStore");

        Assert.assertTrue(bd.isSingleton());
        Assert.assertFalse(bd.isPrototype());

        Assert.assertEquals("com.zhy.service.v1.PetStoreService", bd.getBeanClassName());
        PetStoreService petStore = (PetStoreService) factory.getBean("singletonPetStore");
        Assert.assertNotNull(petStore);

        PetStoreService petStore1 = (PetStoreService) factory.getBean("singletonPetStore");
        Assert.assertTrue(petStore.equals(petStore1));
    }
    @Test
    public void testInvalidBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
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
            Resource resource = new ClassPathResource("XXX.xml");
            reader.loadBeanDefinitions(resource);
        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");

    }


}
