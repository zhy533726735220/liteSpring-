package com.zhy.core.type;

public interface ClassMetadata {

    /**
     * 返回 底层类的名字
     * @return
     */
    String getClassName();

    /**
     * 判断是否是一个接口
     * @return
     */
    boolean isInterface();

    /**
     * 是否是一个抽象类
     * @return
     */
    boolean isAbstract();

    /**
     * 是否是一个final类
     * @return
     */
    boolean isFinal();

    /**
     * 是否有父类
     * @return
     */
    boolean hasSuperClass();

    /**
     * 得到父类的名字
     * @return
     */
    String getSuperClassName();

    /**
     * 得到接口的名字
     * @return
     */
    String[] getInterfaceName();
}
