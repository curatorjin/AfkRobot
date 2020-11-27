package io.github.curatorjin.afk.utils;

import io.github.curatorjin.afk.log.Logger;
import io.github.curatorjin.afk.log.impl.FakeLogger;
import io.github.curatorjin.afk.log.impl.RealLogger;
import io.github.curatorjin.afk.mouse.AfkMouse;
import io.github.curatorjin.afk.mouse.impl.FakeMouse;
import io.github.curatorjin.afk.mouse.impl.RealMouse;
import io.github.curatorjin.afk.tasks.AfkTask;
import io.github.curatorjin.utils.ClassUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class AfkFactory {
    private static AfkMouse afkMouse = null;
    private static Logger afkLogger = null;
    private static AfkTask afkTask = null;
    private static Map<String, AfkTask> taskMap;

    public static AfkMouse getMouse() {
        if (afkMouse == null) {
            afkMouse = AfkConfig.isOperate() ? new RealMouse() : new FakeMouse();
        }
        return afkMouse;
    }

    public static Logger getLogger() {
        if (afkLogger == null) {
            afkLogger = AfkConfig.isLogging() ? new RealLogger() : new FakeLogger();
        }
        return afkLogger;
    }

    public static boolean init() {
        ArrayList<Class<?>> interfaceImpls = ClassUtil.getInterfaceImpls(AfkTask.class);
        List<AfkTask> afkTasks = new LinkedList<>();
        for (Class<?> c : interfaceImpls) {
            try {
                Object o = c.newInstance();
                afkTasks.add((AfkTask) o);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        Map<String, AfkTask> taskMap = afkTasks.stream().collect(Collectors.toMap(AfkTask::getTaskName, a -> a, (k1, k2) -> k2));
        afkTask = taskMap.get(AfkConfig.getTaskName());
        if (afkTask == null) {
            System.out.println("任务名无效！");
            System.out.println("============");
            System.out.println("支持的任务有：");
            taskMap.keySet().forEach(System.out::println);
            System.out.println("============");
            return false;
        }
        return true;
    }

    public static Runnable getTask() {
        return afkTask;
    }
}