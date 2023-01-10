package com.example.athome.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HairdresserProfessionalsList {
    private static Map<String, HairdresserProfessionalDescriptionData> dataMap;

    public static void ReloadFromJSON(JSONObject json) {
        try {
            if(dataMap==null)
                dataMap = new HashMap<>();
            dataMap.clear();
            JSONArray array = json.getJSONArray("hairdressers");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                JSONArray serv = obj.getJSONArray("services");
                ArrayList<HairdresserProvidedServiceData> servList = new ArrayList<>();
                for (int j = 0; j < serv.length(); j++) {
                    JSONObject servObj = serv.getJSONObject(j);
                    String servID = servObj.getString("type");
                    HairdresserServices serviceType = HairdresserServices.MALE_HAIRCUT;
                    switch (servID) {
                        case "male_haircut":
                            serviceType = HairdresserServices.MALE_HAIRCUT;
                            break;
                        case "female_haircut":
                            serviceType = HairdresserServices.FEMALE_HAIRCUT;
                            break;
                        case "hair_coloring":
                            serviceType = HairdresserServices.HAIR_COLORING;
                            break;
                        case "hair_attachment":
                            serviceType = HairdresserServices.HAIR_ATTACHMENT;
                            break;
                        case "hair_highlight":
                            serviceType = HairdresserServices.HAIR_HIGHLIGHT;
                            break;
                    }
                    servList.add(new HairdresserProvidedServiceData(serviceType, servObj.getInt("price")));
                }
                HairdresserProfessionalDescriptionData data = new HairdresserProfessionalDescriptionData(obj.getString("name"), obj.getString("id"), obj.getString("imageID"), servList);
                dataMap.put(obj.getString("id"), data);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static HairdresserProfessionalDescriptionData GetData(String id) {
        return dataMap.get(id);
    }
}
