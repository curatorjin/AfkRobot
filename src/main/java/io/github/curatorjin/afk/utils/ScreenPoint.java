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
    private final int x;

    /**
     * Y坐标
     */
    private final int y;

    /**
     * 坐标点的颜色
     */
    private final int color;

    public ScreenPoint(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }
}
