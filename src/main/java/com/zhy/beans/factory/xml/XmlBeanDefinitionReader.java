package com.zhy.beans.factory.xml;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.factory.BeanDefinitionStoreException;
import com.zhy.beans.factory.support.BeanDefinitionRegistry;
import com.zhy.beans.factory.support.GenericBeanDefinition;
import com.zhy.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author zhy
 * 用于读取xml文件
 */
public class XmlBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    private BeanDefinitionRegistry registry;
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
    public void loadBeanDefinitions(String configFile) {
        InputStream is = null;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            // 遍历<beans>标签下的<bean>
            Element root = doc.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()) {
                Element ele = iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.registry.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("找不到指定的XML文件", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

