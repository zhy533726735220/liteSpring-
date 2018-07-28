package com.zhy.core.type.classreading;

import com.zhy.core.annotation.AnnotationAttributes;
import org.objectweb.asm.AnnotationVisitor;

import java.util.Map;

public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {

    private final String annotationType;

    private final Map<String, AnnotationAttributes> attributesMap;

    private static int ASM_VERSION = 327680;

    AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(ASM_VERSION);

        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public void visitEnd() {
        this.attributesMap.put(this.annotationType, this.attributes);
    }

    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }
}
