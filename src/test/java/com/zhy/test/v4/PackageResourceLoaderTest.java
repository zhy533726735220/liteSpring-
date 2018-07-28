package com.zhy.test.v4;

import com.zhy.core.io.Resource;
import com.zhy.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PackageResourceLoaderTest {

    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.zhy.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
