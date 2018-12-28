package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.InputStream;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午5:20
 **/
public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception{

        Resource resource = new ClassPathResource("goodService-v1.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void testFileSystemResource() throws Exception{

        Resource resource = new FileSystemResource("/Users/tery/Documents/work-branch/threadshold/litespring/src/test/resources/goodService-v1.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
