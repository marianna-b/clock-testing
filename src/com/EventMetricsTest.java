package com;

import com.clock.TestingClock;
import com.clock.TestingClock2;
import org.junit.Assert;

import java.util.List;

public class EventMetricsTest {
    @org.junit.Test
    public void incrementEvent() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock());
        for (int i = 0; i < 600; i++) {
            eventMetrics.incrementEvent("amazing Event");
        }
        EventMetrics.EventStatistics statistics = eventMetrics.getEventStatistics("amazing Event");
        EventMetrics.EventStatistics statistics2 = eventMetrics.getEventStatistics("not so amazing Event");

        System.out.println(statistics.name);
        System.out.println(statistics.toString());
        System.out.println(statistics2.name);
        System.out.println(statistics2.toString());
    }

    @org.junit.Test
    public void incrementEvent2() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock2());
        for (int i = 0; i < 60; i++) {
            eventMetrics.incrementEvent("amazing Event");
        }
        EventMetrics.EventStatistics statistics = eventMetrics.getEventStatistics("amazing Event");
        EventMetrics.EventStatistics statistics2 = eventMetrics.getEventStatistics("not so amazing Event");

        Long sum = 0L;

        for (int i = 0; i < statistics.get().size(); i++) {
            sum += statistics.get().get(i);
        }
        Assert.assertEquals(sum, new Long(59));

        for (int i = 0; i < statistics2.get().size(); i++) {
            Assert.assertEquals(statistics2.get().get(i), new Long(0L));
        }
    }

    @org.junit.Test
    public void allStatistics() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock());
        for (int i = 0; i < 600; i++) {
            eventMetrics.incrementEvent("amazing Event 1");
            eventMetrics.incrementEvent("amazing Event 2");
        }
        List<EventMetrics.EventStatistics> statistics = eventMetrics.allStatistics();
        for (EventMetrics.EventStatistics statistic : statistics) {
            System.out.println(statistic.name);
            System.out.println(statistic.toString());
        }
    }

    @org.junit.Test
    public void allStatistics2() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock2());
        for (int i = 0; i < 120; i++) {
            eventMetrics.incrementEvent("amazing Event 1");
            eventMetrics.incrementEvent("amazing Event 2");
        }
        Long sum = 0L;
        List<EventMetrics.EventStatistics> statistics = eventMetrics.allStatistics();
        for (EventMetrics.EventStatistics statistic : statistics) {
            System.out.println(statistic.name);
            System.out.println(statistic.toString());
            for (int i = 0; i < statistic.get().size(); i++) {
                sum += statistic.get().get(i);
            }
        }
        Assert.assertEquals(sum, new Long(59));
    }

    @org.junit.Test
    public void printAllStatistics() throws Exception {
        EventMetrics eventMetrics = new EventMetrics(new TestingClock());
        for (int i = 0; i < 600; i++) {
            eventMetrics.incrementEvent("amazing Event 1");
            eventMetrics.incrementEvent("amazing Event 2");
        }
        eventMetrics.printAllStatistics();
    }
}