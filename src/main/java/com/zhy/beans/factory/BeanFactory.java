package com.zhy.beans.factory;

import com.zhy.beans.BeanDefinition;

public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanID);

    Object getBean(String beanID);
}
