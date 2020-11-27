package io.github.curatorjin.afk;

import io.github.curatorjin.afk.utils.AfkArgsParser;
import io.github.curatorjin.afk.utils.AfkConfig;
import io.github.curatorjin.afk.utils.AfkFactory;

import java.util.concurrent.TimeUnit;

/**
 * 启动类
 *
 * @author Curatorjin
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        if (!init(args)) {
            System.out.println("初始化失败！");
            System.exit(-1);
        }
        Thread taskThread = new Thread(AfkFactory.getTask());
        taskThread.start();
        int timeout = AfkConfig.getTimeout();
        if (timeout > 0) {
            try {
                TimeUnit.MINUTES.sleep(timeout);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean init(String[] args) {
        return AfkArgsParser.parseArgs(args) && AfkFactory.init();
    }
}
