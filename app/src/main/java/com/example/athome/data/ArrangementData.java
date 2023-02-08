package com.example.athome.data;

import com.example.athome.ui.arrangements.ArrangementLookupOption;

import java.time.LocalDateTime;
import java.util.List;

public class ArrangementData {
    private String title;
    private int cost;
    private ProfessionalType type;
    private ArrangementLookupOption option;
    private String typeString;
    private List<HairdresserServices> servicesList;
    private LocalDateTime time;

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }

    public ProfessionalType getType() {
        return type;
    }

    public String getTypeString() {
        return typeString;
    }

    public ArrangementLookupOption getOption() {
        return option;
    }

    public List<HairdresserServices> getServicesList() {
        return servicesList;
    }

    public LocalDateTime getTime() { return time; }

    public ArrangementData(String title, int cost, ProfessionalType type, String typeString, ArrangementLookupOption option, List<HairdresserServices> servicesList, LocalDateTime time) {
        this.title = title;
        this.cost = cost;
        this.type = type;
        this.typeString = typeString;
        this.option = option;
        this.servicesList = servicesList;
        this.time = time;
    }

    public static String professionalTypeToString(ProfessionalType type) {
        switch(type) {
            case HAIRDRESSER:
                return "Haidresser";
        }
        return "ERROR";
    }
}
