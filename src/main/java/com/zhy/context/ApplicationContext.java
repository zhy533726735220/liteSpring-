package com.zhy.context;

import com.zhy.beans.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {
    @Override
    Object getBean(String beanID);
}
