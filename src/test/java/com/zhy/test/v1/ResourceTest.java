package com.zhy.test.v1;

import com.zhy.core.io.ClassPathResource;
import com.zhy.core.io.FileSystemResource;
import com.zhy.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception {
        Resource r = new ClassPathResource("petstore-v1.xml");
        InputStream is = null;

        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    @Test
    public void testFileSystemResource() throws Exception {
        Resource r = new FileSystemResource("F:\\petstore-v1.xml");
        InputStream is = null;

        try {
            is = r.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }
}
