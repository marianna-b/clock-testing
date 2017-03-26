package com.clock;

import java.util.Date;
import java.util.Random;

public class TestingClock implements AmazingClock{
    private Long l = new Date().getTime() / 1000 / 60;
    private int i = 0;
    private int j = 0;
    private Random random = new Random();

    @Override
    public Long getCurrentMinutes() {
        if (i < j) {
            i++;
            return l;
        } else {
            j = random.nextInt() % 3 + 2;
            i = 0;
            l += random.nextInt() % 5;
            return l;
        }
    }
}
