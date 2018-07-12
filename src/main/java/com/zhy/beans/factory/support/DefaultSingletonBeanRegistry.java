package com.zhy.beans.factory.support;

import com.zhy.beans.factory.config.SingletonBeanRegistry;
import com.zhy.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    @Override
    public void registerSingleton(String beanID, Object singletonObject) {
        Assert.notNull(beanID, "beanId不能为空");
        Object oldObject = this.singletonObjects.get(beanID);
        if (oldObject != null) {
            throw new IllegalStateException("因为" + beanID + "是单例类,不能重复注册");
        }
        this.singletonObjects.put(beanID, singletonObject);
    }

    @Override
    public Object getSingleton(String beanID) {
        return this.singletonObjects.get(beanID);
    }
}
