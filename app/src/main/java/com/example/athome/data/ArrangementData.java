package com.example.athome.data;

import com.example.athome.ui.arrangements.ArrangementLookupOption;

import java.util.List;

public class ArrangementData {
    private String title;
    private String cost;
    private ProfessionalType type;
    private ArrangementLookupOption option;
    private String typeString;
    private List<HairdresserServices> servicesList;

    public String getTitle() {
        return title;
    }

    public String getCost() {
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

    public ArrangementData(String title, String cost, ProfessionalType type, String typeString, ArrangementLookupOption option, List<HairdresserServices> servicesList) {
        this.title = title;
        this.cost = cost;
        this.type = type;
        this.typeString = typeString;
        this.option = option;
        this.servicesList = servicesList;
    }

    public static String professionalTypeToString(ProfessionalType type) {
        switch(type) {
            case HAIRDRESSER:
                return "Haidresser";
        }
        return "ERROR";
    }
}
