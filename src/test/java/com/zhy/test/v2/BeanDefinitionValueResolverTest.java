package com.zhy.test.v2;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.factory.config.RuntimeBeanReference;
import com.zhy.beans.factory.config.TypedStringValue;
import com.zhy.beans.factory.support.BeanDefinitionRegistry;
import com.zhy.beans.factory.support.BeanDefinitionValueResolver;
import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.core.io.ClassPathResource;
import com.zhy.dao.v2.AccountDao;
import org.junit.Assert;
import org.junit.Test;

public class BeanDefinitionValueResolverTest {

    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petStore-v2.xml"));
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolverValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }

    @Test
    public void testResolveTypedStringValue() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petStore-v2.xml"));
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolverValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
