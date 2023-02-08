package com.example.athome.data;

import com.example.athome.R;

public enum HairdresserServices {
    MALE_HAIRCUT(1,R.string.product_hairdresser_male_haircut,"male_haircut"),
    FEMALE_HAIRCUT(2,R.string.product_hairdresser_female_haircut,"female_haircut"),
    CHILD_HAIRCUT(3,R.string.product_hairdresser_child_haircut,"child_haircut"),
    HAIR_COLORING(4,R.string.product_hairdresser_hair_coloring,"coloring"),
    HAIR_HIGHLIGHT(5, R.string.product_hairdresser_hair_highlight, "highlight"),
    HAIR_ATTACHMENT(6, R.string.product_hairdresser_attachment, "attachment");

    private final int value;
    private final int resID;
    private final String code;

    HairdresserServices(int value, int resID, String code) {
        this.value = value;
        this.resID = resID;
        this.code = code;
    }

    public String GetStandardCode() {
        return this.code;
    }
}
