package io.github.curatorjin.afk.utils;

/**
 * 坐标点
 *
 * @author Curatorjin
 * @version 1.0
 */
public class ScreenPoint {
    /**
     * X坐标
     */
    private int x;

    /**
     * Y坐标
     */
    private int y;

    /**
     * 坐标点的颜色
     */
    private int color;

    public ScreenPoint(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

}
