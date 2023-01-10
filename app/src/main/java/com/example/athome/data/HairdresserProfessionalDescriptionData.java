package com.example.athome.data;

import java.util.List;

public class HairdresserProfessionalDescriptionData {
    private String name;
    private String id;
    private String imageID;
    private List<HairdresserProvidedServiceData> servicesList;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImageID() {
        return imageID;
    }

    public List<HairdresserProvidedServiceData> getServicesList() {
        return servicesList;
    }

    public HairdresserProfessionalDescriptionData(String name, String id, String imageID, List<HairdresserProvidedServiceData> servicesList) {
        this.name = name;
        this.id = id;
        this.imageID = imageID;
        this.servicesList = servicesList;
    }
}
