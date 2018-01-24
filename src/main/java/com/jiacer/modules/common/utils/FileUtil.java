package com.jiacer.modules.common.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class FileUtil {

    public static final String ENCODING = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(ch.qos.logback.core.util.FileUtil.class);

    /**
     * 读取src目录下的配置文件。
     *
     * @param source 配置文件名，不带"/"
     * @return 文件内容
     */
    public static String readSourceAsString(String source) throws IOException, URISyntaxException {
        try {
            URI uri = FileUtil.class.getResource("/" + source).toURI();
            return FileUtils.readFileToString(new File(uri), ENCODING);
        } catch (IOException e) {
            logger.error("读取文件出错", e);
            throw new IOException("读取文件出错:" + e.getMessage());
        }
    }

    /**
     * 读取配置文件。该方法不会缓存已读文件。
     *
     * @param propertyFile 配置文件名，不带"/"
     * @return 文件内容
     */
    public static Properties readProperty(String propertyFile) throws IOException {
        try {
            Properties props = new Properties();
            props.load(FileUtil.class.getResourceAsStream("/" + propertyFile));
            return props;
        } catch (IOException e) {
            logger.error("读取文件出错", e);
            throw new IOException("读取文件出错:" + e.getMessage());
        }
    }

}
