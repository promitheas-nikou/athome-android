package com.example.athome.data;

import com.example.athome.ui.arrangements.ArrangementLookupOption;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArrangementsList {
    private static Map<Integer, ArrangementData> arrangements = new HashMap<>();
    private static int latestID = 7;
    static {
        arrangements.put(1, new ArrangementData("FIRST ARRANGEMENT", 3162, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(2, new ArrangementData("Μαρία Παπαδοπούλου", 6251, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(3, new ArrangementData("Αναστασία Παπανικολάου", 1524, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.CANCELLED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(4, new ArrangementData("Ελένη Δημητριάδου", 9285, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(5, new ArrangementData("Αντωνία Καραπέτρου", 8371, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(6, new ArrangementData("Ιωάννης Παπαδόπουλος", 5425, ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
    }

    ;

    public static ArrangementData getArrangementData(int id) {
        return arrangements.get(id);
    }

    public static Set<Integer> getArrangementIDsList() {
        return arrangements.keySet();
    }

    public static Collection<ArrangementData> getArrangementDataList() {
        return arrangements.values();
    }

    public static Set<Map.Entry<Integer,ArrangementData>> getEntrySet() {
        return arrangements.entrySet();
    }

    public static int addArrangement(ArrangementData d) {
        arrangements.put(latestID, d);
        return latestID++;
    }

}
