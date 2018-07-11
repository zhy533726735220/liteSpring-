package com.zhy.context.support;

import com.zhy.beans.factory.support.DefaultBeanFactory;
import com.zhy.beans.factory.xml.XmlBeanDefinitionReader;
import com.zhy.context.ApplicationContext;
import com.zhy.core.io.FileSystemResource;
import com.zhy.core.io.Resource;

public class FileSystemXmlApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    public FileSystemXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new FileSystemResource(configFile);
        reader.loadBeanDefinitions(resource);
    }
    @Override
    public Object getBean(String beanID) {
        return this.factory.getBean(beanID);
    }
}
