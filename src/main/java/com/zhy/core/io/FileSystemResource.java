package com.zhy.core.io;

import com.zhy.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

    private String path;
    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "路径不能为空");
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return this.file.getAbsolutePath();
    }
}
