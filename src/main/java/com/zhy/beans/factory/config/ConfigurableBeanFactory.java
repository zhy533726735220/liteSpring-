package com.zhy.beans.factory.config;

import com.zhy.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory{
    void setBeanClassLoader(ClassLoader beanClassLoader);
    ClassLoader getBeanClassLoader();
}
