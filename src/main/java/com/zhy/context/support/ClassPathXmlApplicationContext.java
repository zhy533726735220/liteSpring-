package com.zhy.context.support;

import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.context.ApplicationContext;
import com.zhy.core.io.ClassPathResource;
import com.zhy.core.io.Resource;

public class ClassPathXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
