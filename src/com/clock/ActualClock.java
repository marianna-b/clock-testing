package com.clock;

import java.util.Date;

public class ActualClock implements AmazingClock {
    private Date date = new Date();
    @Override
    public Long getCurrentMinutes() {
        return date.getTime() / 1000 / 60;
    }
}
