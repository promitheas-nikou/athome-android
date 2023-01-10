package com.example.athome.data;

import android.util.Pair;

import com.example.athome.ui.arrangements.ArrangementLookupOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArrangementsList {
    private static Map<Integer, ArrangementData> arrangements = new HashMap<>();

    static {
        arrangements.put(1, new ArrangementData("FIRST ARRANGEMENT", "523$", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(2, new ArrangementData("Μαρία Παπαδοπούλου", "42€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(3, new ArrangementData("Αναστασία Παπανικολάου", "32€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.CANCELLED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(4, new ArrangementData("Ελένη Δημητριάδου", "32€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(5, new ArrangementData("Αντωνία Καραπέτρου", "52€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.PENDING, Arrays.asList(), LocalDateTime.now()));
        arrangements.put(6, new ArrangementData("Ιωάννης Παπαδόπουλος", "262€", ProfessionalType.HAIRDRESSER, "Hairdresser", ArrangementLookupOption.COMPLETED, Arrays.asList(), LocalDateTime.now()));
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

}
