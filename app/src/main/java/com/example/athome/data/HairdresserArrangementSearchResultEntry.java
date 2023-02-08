package com.example.athome.data;

public final class HairdresserArrangementSearchResultEntry {
    private String name;
    private String id;
    private int totalCost;

    public int getTotalCost() {
        return totalCost;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public HairdresserArrangementSearchResultEntry(String name, String id, int totalCost) {
        this.name = name;
        this.id = id;
        this.totalCost = totalCost;
    }
}
