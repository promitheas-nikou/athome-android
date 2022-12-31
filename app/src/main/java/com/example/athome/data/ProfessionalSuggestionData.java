package com.example.athome.data;

public class ProfessionalSuggestionData {
    private String title;
    private String name;
    private String cost;
    private ProfessionalType type;
    private String typeString;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
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

    public ProfessionalSuggestionData(String title, String name, String cost, ProfessionalType type, String typeString) {
        this.title = title;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.typeString = typeString;
    }

    public static String professionalTypeToString(ProfessionalType type) {
        switch(type) {
            case HAIRDRESSER:
                return "Haidresser";
        }
        return "ERROR";
    }

    public ProfessionalSuggestionData(String title, String name, String cost, ProfessionalType type) {
        this(title,name,cost,type,professionalTypeToString(type));
    }
}
