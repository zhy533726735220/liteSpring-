package com.zhy.beans.factory.support;

import com.zhy.beans.BeanDefinition;
import com.zhy.beans.ConstructorArgument;
import com.zhy.beans.SimpleTypeConverter;
import com.zhy.beans.TypeMismatchException;
import com.zhy.beans.factory.BeanCreationException;
import com.zhy.beans.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ConstructorResolver {

    protected final Log logger = LogFactory.getLog(getClass());

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(final BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            //todo 可以做成缓存beanClass，因为创建ClassLoader是非常费时的操作
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(bd.getID(), "Instantiation of bean failed, can't resolve class", e);
        }

        // candidates:候选人
        Constructor<?>[] candidates = beanClass.getConstructors();

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);

        ConstructorArgument cargs = bd.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];

            boolean result = this.valuesMatchTypes(parameterTypes, cargs.getArgumentValues(), argsToUse, valueResolver, typeConverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }

        // 找不到一个合适的构造函数
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getID(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException( bd.getID(), "can't find a create instance using " + constructorToUse);
        }

    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes, List<ConstructorArgument.ValueHolder> valueHolders, Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);

            // 获得的参数，可能是TypedStringValue, 也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();
            Object resolveValue = valueResolver.resolverValueIfNecessary(originalValue);

            try {
                Object convertedValue = typeConverter.convertIfNecessary(resolveValue, parameterTypes[i]);
                // 转型成功记录下来
                argsToUse[i] = convertedValue;
            } catch (TypeMismatchException e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }


}
