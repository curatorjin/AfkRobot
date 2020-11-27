package io.github.curatorjin.afk.scene;

import io.github.curatorjin.afk.utils.ScreenPoint;

import java.util.List;

/**
 * 场景模板
 *
 * @author Curatorjin
 * @version 1.0
 */
public class SceneTemplate implements Scene {
    private List<ScreenPoint> judgePoints;

    private String name;

    public List<ScreenPoint> getJudgePoints() {
        return judgePoints;
    }

    public void setJudgePoints(List<ScreenPoint> judgePoints) {
        this.judgePoints = judgePoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isScene() {
        return false;
    }

    @Override
    public void action() {

    }
}
