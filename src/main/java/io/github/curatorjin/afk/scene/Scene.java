package io.github.curatorjin.afk.scene;

/**
 * 场景接口
 *
 * @author Curatorjin
 * @version 1.0
 */
public interface Scene {
    String getName();

    boolean isScene();

    void action();
}
