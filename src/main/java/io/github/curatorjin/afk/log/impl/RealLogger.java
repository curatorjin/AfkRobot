package io.github.curatorjin.afk.log.impl;

import io.github.curatorjin.afk.log.Logger;

public class RealLogger implements Logger {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void error(String msg) {
        System.err.println(msg);
    }
}
