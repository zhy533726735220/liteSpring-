package com.zhy.core.type.classreading;

import com.zhy.core.type.ClassMetadata;
import com.zhy.util.ClassUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    private static int ASM_VERSION = 327680;

    public ClassMetadataReadingVisitor() {
        super(ASM_VERSION);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = ClassUtils.convertResourcePathToClassName(name);
        this.isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
        this.isAbstract = (access & Opcodes.ACC_ABSTRACT) != 0;
        this.isFinal = (access & Opcodes.ACC_FINAL) != 0;
        if (superName != null) {
            this.superClassName = ClassUtils.convertResourcePathToClassName(superName);
        }
        this.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public boolean isInterface() {
        return this.isInterface;
    }

    @Override
    public boolean isAbstract() {
        return this.isAbstract;
    }

    public boolean isConcrete() {
        return !(this.isInterface || this.isAbstract);
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }

    @Override
    public boolean hasSuperClass() {
        return this.superClassName != null;
    }

    @Override
    public String getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public String[] getInterfaceName() {
        return new String[0];
    }

}
