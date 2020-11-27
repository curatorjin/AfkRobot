package io.github.curatorjin.afk.mouse;

public interface AfkMouse {
    void leftClick(int x, int y);
    void rightClick(int x, int y);

    void scroll(int x, int y);

    void delay(int last);
}
