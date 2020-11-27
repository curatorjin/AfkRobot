package io.github.curatorjin.afk.utils;

import java.awt.*;

public final class ScreenUtil {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static boolean matchScene(ScreenPoint... point) {
        for (ScreenPoint screenPoint : point) {
            if (getPointRGB(screenPoint.getX(), screenPoint.getY()) != screenPoint.getColor()) {
                return false;
            }
        }
        return true;
    }

    protected static int getPointRGB(int x, int y) {
        return robot.getPixelColor(x, y).getRGB();
    }
}
