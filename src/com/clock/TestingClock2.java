package com.clock;

public class TestingClock2 implements AmazingClock{
    private Long i = 0L;

    @Override
    public Long getCurrentMinutes() {
        i++;
        return i;
    }
}
