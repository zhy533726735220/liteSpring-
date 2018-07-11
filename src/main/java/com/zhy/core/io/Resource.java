package com.zhy.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhy
 * 读取文件的接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

    /**
     * 文件路径
     * @return
     */
    String getDescription();
}
