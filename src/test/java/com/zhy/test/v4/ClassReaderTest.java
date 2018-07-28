package com.zhy.test.v4;

import com.zhy.core.annotation.AnnotationAttributes;
import com.zhy.core.io.ClassPathResource;
import com.zhy.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.zhy.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/zhy/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("com.zhy.service.v4.PetStoreService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceName().length);

    }

    @Test
    public void testGetAnnotation() throws Exception {
        ClassPathResource resource = new ClassPathResource("com/zhy/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "com.zhy.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));
    }
}
