package org.litespring.core.io;

import java.io.InputStream;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午10:50
 **/
public interface Resource {

    InputStream getInputStream() throws Exception;

    String getDescription();

}
