package com;

import com.clock.AmazingClock;

import java.util.*;

class EventMetrics {

    private final AmazingClock clock;
    private Map<String, EventStatistics> stats;

    EventMetrics(AmazingClock clock) {
        this.clock = clock;
        this.stats = new HashMap<>();
    }

    void incrementEvent(String eventName) {
        Long t = clock.getCurrentMinutes();
        EventStatistics e = stats.getOrDefault(eventName, new EventStatistics(eventName));
        e.add(t);
        stats.put(eventName, e);
    }

    EventStatistics getEventStatistics(String eventName) {
        final Long t = clock.getCurrentMinutes();
        EventStatistics e = stats.getOrDefault(eventName, new EventStatistics(eventName));
        e.process(t);
        return e;
    }

    List<EventStatistics> allStatistics() {
        final Long t = clock.getCurrentMinutes();
        List<EventStatistics> list = new ArrayList<>();
        for (Map.Entry<String, EventStatistics> entry : stats.entrySet()) {
            entry.getValue().process(t);
            list.add(entry.getValue());
        }
        return list;
    }

    void printAllStatistics() {
        List<EventStatistics> list = allStatistics();
        for (EventMetrics.EventStatistics statistic : list) {
            System.out.println(statistic.name);
            System.out.println(statistic.toString());
        }
    }

    class EventStatistics {
        String name;
        private Map<Long, Long> stats = new HashMap<>();
        Long l = 0L;

        EventStatistics(String s) {
            name = s;
        }

        void process(Long t) {
            l = t;
            Map <Long, Long> newStats = new HashMap<>();
            for (Long i = t - 59; i <= t ; i++) {
                if (stats.containsKey(i))
                    newStats.put(i, stats.get(i));
            }
            stats = newStats;
        }

        void add(Long t) {
            l = t;
            stats.put(t, stats.getOrDefault(t, (long) 0) + 1);
            process(t);
        }

        List<Long> get() {
            List <Long> list = new ArrayList<>();
            for (Long i = l - 59; i <= l ; i++) {
                list.add(stats.getOrDefault(i, (long) 0));
            }
            return list;
        }

        public String toString() {
            List<Long> list = get();
            String s = "";
            for (Long aList : list) {
                s += aList.toString() + " ";
            }
            return s;
        }

    }
}
