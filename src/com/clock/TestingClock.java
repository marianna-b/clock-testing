package com.clock;

import java.util.Date;

public class TestingClock implements AmazingClock{
    private Long l = new Date().getTime() / 1000 / 60;
    private int i = 0;

    @Override
    public Long getCurrentMinutes() {
        if (i < 5) {
            i++;
            return l;
        } else {
            i = 0;
            l += 2;
            return l;
        }
    }
}
