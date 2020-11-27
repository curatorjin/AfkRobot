/*
 *
 * 文件名: CloseUtil.java
 * 描述: 用来关闭流的工具类，自带了异常捕获
 * 创建人: 0newing
 * 时间: 2018/12/5  18:49
 *
 */
package io.github.curatorjin.utils;

import java.io.Closeable;
import java.io.IOException;


/**
 * 关流等
 *
 * @author : 0newing
 * @version : 1.0
 */
public final class CloseUtil {
    private CloseUtil() {
    }

    /**
     * 关流
     *
     * @param closeable 待关闭的流
     */
    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeable = null;
        }
    }
}
