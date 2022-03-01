package com.yakzhanov.flatseeker.model.dto.dict;

import com.yakzhanov.flatseeker.model.AnimalsStatus;
import lombok.Data;

@Data
public class AnimalStatusDict {

    private String key;
    private String title;

    public AnimalStatusDict(AnimalsStatus status) {
        this.key = status.name();
        this.title = status.getTitle();
    }
}
