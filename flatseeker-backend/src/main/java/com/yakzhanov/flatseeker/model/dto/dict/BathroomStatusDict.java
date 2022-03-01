package com.yakzhanov.flatseeker.model.dto.dict;

import com.yakzhanov.flatseeker.model.BathroomStatus;
import lombok.Data;

@Data
public class BathroomStatusDict {

    private String key;
    private String title;

    public BathroomStatusDict(BathroomStatus status) {
        this.key = status.name();
        this.title = status.getTitle();
    }
}
