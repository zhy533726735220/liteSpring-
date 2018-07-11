package com.zhy.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhy
 * 读取文件的接口,把文件变成流
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

    /**
     * 文件路径
     * @return
     */
    String getDescription();
}
