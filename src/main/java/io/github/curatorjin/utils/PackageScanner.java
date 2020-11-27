/*
 *
 * 文件名: PackageScanner.java
 * 描述: 包工具
 * 创建人: 0newing
 * 时间: 2019/1/18  22:22
 *
 */
package io.github.curatorjin.utils;

import io.github.curatorjin.afk.Main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 包扫描工具
 *
 * @author : 0newing
 * @version : 1.0
 */
public final class PackageScanner {
    private PackageScanner() {
    }

    /**
     * 获取一个接口的所有实现类
     *
     * @param target 目标接口
     * @return 实现类集合
     */
    public static ArrayList<Class<?>> getInterfaceImpls(Class<?> target) {
        ArrayList<Class<?>> subclasses = new ArrayList<>();
        try {
            if (target.isInterface()) {
                List<Class<?>> allTasks = new LinkedList<>();
                PackageScanner.getAllClasses(allTasks, "io.github.curatorjin.afk.tasks.impl");
                for (Class<?> c : allTasks) {
                    Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(c.getInterfaces()));
                    if (interfaces.contains(target)) {
                        subclasses.add(c);
                    }
                }
            } else {
                System.err.println("Class对象不是一个interface");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return subclasses;
    }

    /**
     * 获取包路径下的所有类
     *
     * @param list        返回的Class集合
     * @param packagePath 包路径
     */
    public static void getAllClasses(List<Class<?>> list, String packagePath) {
        String jarPathInfo = PackageScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (!jarPathInfo.endsWith(".jar")) {
            getAllClassesInFile(list, packagePath);
        } else {
            JarFile jarFile = null;

            try {
                jarFile = new JarFile(jarPathInfo.substring(jarPathInfo.indexOf("/")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getAllClassesInJar(list, packagePath, jarFile);
        }

    }

    /**
     * 获取Jar包中某包下的所有类
     *
     * @param list        返回的Class集合
     * @param packagePath 包路径
     * @param jarFile     Jar文件
     */
    private static void getAllClassesInJar(List<Class<?>> list, String packagePath, JarFile jarFile) {
        if (null == jarFile) {
            return;
        }
        ClassLoader loader = PackageScanner.class.getClassLoader();
        packagePath = packagePath.replace(".", "/");
        Enumeration<JarEntry> entrys = jarFile.entries();
        while (entrys.hasMoreElements()) {
            JarEntry entry = entrys.nextElement();
            String entryName = entry.getName();
            if (entryName.endsWith(".class") && entryName.startsWith(packagePath)) {
                entryName = entryName.replace("/", ".");
                entryName = entryName.substring(0, entryName.lastIndexOf("."));
                Class<?> clazz = null;

                try {
                    clazz = loader.loadClass(entryName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (null != clazz) {
                    list.add(clazz);
                }
            }
        }
    }

    /**
     * 获取文件夹中的所有类
     *
     * @param list        返回的Class集合
     * @param packagePath 包路径
     */
    private static void getAllClassesInFile(List<Class<?>> list, String packagePath) {
        String filePath = packagePath.replace(".", "/");
        if (packagePath.endsWith(".class")) {
            filePath = filePath.replace("/class", ".class");
        }
        URL rootPath = PackageScanner.class.getClassLoader().getResource(
                filePath);

        File file = null;
        try {
            if (null != rootPath) {
                file = new File(rootPath.toURI());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (null == file) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    getAllClasses(list, packagePath + "." + f.getName());
                }
            }
        } else {
            if (file.getName().endsWith(".class")) {
                try {
                    Class<?> clazz = PackageScanner.class.getClassLoader().loadClass(
                            packagePath.replace(".class", ""));
                    list.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}