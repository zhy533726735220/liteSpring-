package com.zhy.beans.factory;

import com.zhy.beans.BeansException;

/**
 * @author zhy
 * 创建Bean出错时抛出的异常
 */
public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanCreationException(String beanName, String msg) {
        super("创建实体类错误，无法创建'" + beanName + "':" + msg);
    }

    public BeanCreationException(String beanName,String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    public String getBeanName() {
        return this.beanName;
    }

}
