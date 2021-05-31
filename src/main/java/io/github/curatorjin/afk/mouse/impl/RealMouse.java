package io.github.curatorjin.afk.mouse.impl;

import io.github.curatorjin.afk.mouse.AfkMouse;

import java.awt.*;
import java.awt.event.InputEvent;

public class RealMouse implements AfkMouse {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void leftClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(200);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void rightClick(int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(200);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    public static void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }

    public void scroll(int x, int y) {
        robot.mouseMove(x, y);
        robot.delay(50);
        robot.mouseWheel(2000);
        robot.delay(50);
        robot.mouseWheel(2000);
        robot.delay(50);
        robot.mouseWheel(2000);
        robot.delay(50);
    }

    public void delay(int ms) {
        robot.delay(ms);
    }
}
