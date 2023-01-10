package com.example.athome.data;

import com.example.athome.R;

public enum HairdresserServices {
    MALE_HAIRCUT(1,R.string.product_hairdresser_male_haircut),
    FEMALE_HAIRCUT(2,R.string.product_hairdresser_female_haircut),
    HAIR_COLORING(3,R.string.product_hairdresser_hair_coloring),
    HAIR_HIGHLIGHT(4, R.string.product_hairdresser_hair_highlight),
    HAIR_ATTACHMENT(5, R.string.product_hairdresser_attachment);

    private final int value;
    private final int resID;

    HairdresserServices(int value, int resID) {
        this.value = value;
        this.resID = resID;
    }
}
