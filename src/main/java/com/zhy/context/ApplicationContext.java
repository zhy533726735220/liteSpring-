package com.zhy.context;

import com.zhy.beans.factory.config.ConfigurableBeanFactory;

public interface ApplicationContext extends ConfigurableBeanFactory {
    @Override
    Object getBean(String beanID);
}
