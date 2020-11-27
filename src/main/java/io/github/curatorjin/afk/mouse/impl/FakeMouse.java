package io.github.curatorjin.afk.mouse.impl;

import io.github.curatorjin.afk.mouse.AfkMouse;

import java.util.concurrent.TimeUnit;

public class FakeMouse implements AfkMouse {
    @Override
    public void leftClick(int x, int y) {

    }

    @Override
    public void rightClick(int x, int y) {

    }

    @Override
    public void scroll(int x, int y) {

    }

    @Override
    public void delay(int last) {
        try {
            TimeUnit.MILLISECONDS.sleep(last);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
