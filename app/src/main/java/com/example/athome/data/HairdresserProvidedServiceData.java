package com.example.athome.data;

public class HairdresserProvidedServiceData {
    private HairdresserServices service;
    private int cost;

    public HairdresserProvidedServiceData(HairdresserServices service, int cost) {
        this.service = service;
        this.cost = cost;
    }

    public HairdresserServices getService() {
        return service;
    }

    public int getCost() {
        return cost;
    }
}
