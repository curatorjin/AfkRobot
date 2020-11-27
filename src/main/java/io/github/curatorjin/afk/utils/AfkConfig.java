package io.github.curatorjin.afk.utils;

import io.github.curatorjin.afk.tasks.AfkTask;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class AfkConfig {
    private static final AtomicBoolean withLog = new AtomicBoolean(false);

    private static final AtomicBoolean operate = new AtomicBoolean(false);

    private static String taskName = null;

    private static final AtomicInteger timeout = new AtomicInteger(0);

    public static boolean isLogging() {
        return withLog.get();
    }

    public static boolean isOperate() {
        return operate.get();
    }

    public static String getTaskName() {
        return taskName;
    }

    public static int getTimeout() {
        return timeout.get();
    }

    protected static void setLogging(boolean logConfig) {
        withLog.set(logConfig);
    }

    protected static void setOperate(boolean operateConfig) {
        operate.set(operateConfig);
    }

    protected static void setTaskName(String targetTaskName) {
        taskName = targetTaskName;
    }

    protected static void setTimeout(int optionTimeout) {
        timeout.set(optionTimeout);
    }
}
