package org.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResourceLoader;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/6
 **/
public class PackageResourceLoaderTest {

    @Test
    public void testLoadResource() {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("org.litespring.dao.v4");
        Assert.assertEquals(resources.length, 2);

    }
}
