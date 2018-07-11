package com.zhy.beans.factory;

import com.zhy.beans.BeanDefinition;

public interface BeanFactory {
    Object getBean(String beanID);
}
