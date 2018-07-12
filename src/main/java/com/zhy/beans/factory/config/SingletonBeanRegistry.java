package com.zhy.beans.factory.config;

/**
 * @author zhy
 * 单例类注册
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanID, Object singletonObject);
    Object getSingleton(String beanID);
}
