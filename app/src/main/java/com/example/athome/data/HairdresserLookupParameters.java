package com.example.athome.data;

import java.util.Map;

public class HairdresserLookupParameters {
    private int year;
    private int month;
    private int date;
    private int time;
    private int maxCost;
    private Map<HairdresserServices, Integer> services;

    public HairdresserLookupParameters(int year, int month, int date, int time, int maxCost, Map<HairdresserServices, Integer> services) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.time = time;
        this.maxCost = maxCost;
        this.services = services;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public int getMaxCost() {
        return maxCost;
    }

    public Map<HairdresserServices, Integer> getServices() {
        return services;
    }
}
