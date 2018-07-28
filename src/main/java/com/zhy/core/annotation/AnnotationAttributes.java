package com.zhy.core.annotation;

import com.zhy.util.Assert;

import java.util.LinkedHashMap;

@SuppressWarnings("serial")
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

    public AnnotationAttributes() {

    }

    public AnnotationAttributes(int initialCapacity) {
        super(initialCapacity);
    }

    public String getString(String attributeName) {
        return this.doGet(attributeName, String.class);
    }

    public String[] getStringArray(String attributeName) {
        return this.doGet(attributeName, String[].class);
    }

    public boolean getBoolean(String attributeName) {
        return this.doGet(attributeName, Boolean.class);
    }

    public <N extends Number> N getNumber(String attributeName) {
        return (N) this.doGet(attributeName, Integer.class);
    }

    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) this.doGet(attributeName, Enum.class);
    }

    public <T> Class<? extends T> getClass(String attributeName) {
        return this.doGet(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return this.doGet(attributeName, Class[].class);
    }

    private <T> T doGet(String attributeName, Class<T> expectedType) {
        Object value = this.get(attributeName);
        Assert.notNull(value, String.format("Attribute '%s' not found", attributeName));
        return (T) value;
    }
}
