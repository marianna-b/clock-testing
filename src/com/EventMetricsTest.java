package com;

import com.clock.TestingClock;

import java.util.List;

public class EventMetricsTest {
    @org.junit.Test
    public void incrementEvent() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock());
        for (int i = 0; i < 601; i++) {
            eventMetrics.incrementEvent("amazing Event");
        }
        EventMetrics.EventStatistics statistics = eventMetrics.getEventStatistics("amazing Event");
        System.out.println(statistics.name);
        System.out.println(statistics.toString());
    }

    @org.junit.Test
    public void allStatistics() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock());
        for (int i = 0; i < 601; i++) {
            eventMetrics.incrementEvent("amazing Event 1");
            eventMetrics.incrementEvent("amazing Event 2");
        }
        List<EventMetrics.EventStatistics> statistics = eventMetrics.allStatistics();
        for (EventMetrics.EventStatistics statistic : statistics) {
            System.out.println(statistic.name);
            System.out.println(statistic.toString());
        }
    }

}