package org.litespring.core.io;

import java.io.*;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午10:51
 **/
public class FileSystemResource implements Resource {

    private String path;
    private File file;

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = new FileInputStream(this.file);
        if (inputStream == null) {
            throw new FileNotFoundException("file can't found:" + path);
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
