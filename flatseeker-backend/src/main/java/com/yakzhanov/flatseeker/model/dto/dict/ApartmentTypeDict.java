package com.yakzhanov.flatseeker.model.dto.dict;

import com.yakzhanov.flatseeker.model.ApartmentType;
import lombok.Data;

@Data
public class ApartmentTypeDict {

    private String key;
    private String title;

    public ApartmentTypeDict(ApartmentType type) {
        this.key = type.name();
        this.title = type.getTitle();
    }
}
