package com.zhy.core.io.support;

import com.zhy.core.io.FileSystemResource;
import com.zhy.core.io.Resource;
import com.zhy.util.Assert;
import com.zhy.util.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PackageResourceLoader {

    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private final  ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, "Resource must not be null");
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Resource[] getResources(String basePackage) throws IOException {
        Assert.notNull(basePackage, "basePackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        String path = url.getFile();
        path = URLDecoder.decode(path, "utf-8");

        File rootDir = new File(path);

        Set<File> matchingFiles = this.retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;

    }

    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {

        // 文件路径是否存在
        if (!rootDir.exists()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping[" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }

        // 检查路径是否是文件夹
        if (!rootDir.isDirectory()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }

        // 文件夹是否允许读取
        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() + "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }

        Set<File> result = new LinkedHashSet<File>(8);
        this.doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    /**
     * 递归的找一个目录下的所有文件
     * @param dir
     * @param result
     * @throws IOException
     */
    protected void doRetrieveMatchingFiles(File dir, Set<File> result) throws IOException {
        File[] dirContents = dir.listFiles();
        if (dirContents == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return;
        }

        for (File content : dirContents) {
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() + "] because the application is not allowed to read the directory");
                    }
                } else {
                    this.doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }
        }
    }

}
