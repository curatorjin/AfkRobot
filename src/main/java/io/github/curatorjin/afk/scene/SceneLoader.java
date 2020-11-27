package io.github.curatorjin.afk.scene;

import io.github.curatorjin.afk.utils.ScreenPoint;
import io.github.curatorjin.utils.CloseUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 场景导入器
 *
 * @author Curatorjin
 * @version 1.0
 */
public class SceneLoader {
    public List<Scene> loadScene(File configFile) {
        List<Scene> scenes = new LinkedList<>();
        BufferedInputStream bufferedInputStream = null;
        FileInputStream fileInputStream = null;
        Properties config = new Properties();
        try {
            fileInputStream = new FileInputStream(configFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            config.load(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeStream(bufferedInputStream);
        }
        for (String name : config.stringPropertyNames()) {
            SceneTemplate scene = new SceneTemplate();
            scene.setName(name);
            scenes.add(scene);

        }
        return scenes;
    }

    private List<ScreenPoint> transPoints(String pointStr) {
        List<ScreenPoint> points = new LinkedList<>();

        return points;
    }
}
