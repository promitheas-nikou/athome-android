package com.example.athome.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HairdresserProfessionalDescriptionData {
    private String name;
    private String id;
    private String imageID;
    private Map<HairdresserServices, HairdresserProvidedServiceData> servicesMap;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImageID() {
        return imageID;
    }

    public Collection<HairdresserServices> getServiceKeysList() {
        return servicesMap.keySet();
    }

    public Map<HairdresserServices, HairdresserProvidedServiceData> getServicesMap() {
        return servicesMap;
    }

    public HairdresserProfessionalDescriptionData(String name, String id, String imageID, Map<HairdresserServices, HairdresserProvidedServiceData> servicesMap) {
        this.name = name;
        this.id = id;
        this.imageID = imageID;
        this.servicesMap = servicesMap;
    }
}
