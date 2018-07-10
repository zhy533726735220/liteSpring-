package com.zhy.beans.factory;

import com.zhy.beans.BeansException;

/**
 * @author zhy
 * 读取XML文件出错时抛出的异常
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
