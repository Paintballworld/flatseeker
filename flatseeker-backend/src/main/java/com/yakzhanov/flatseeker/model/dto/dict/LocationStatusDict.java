package com.yakzhanov.flatseeker.model.dto.dict;

import com.yakzhanov.flatseeker.model.LocationStatus;
import com.yakzhanov.flatseeker.model.ProcessStatus;
import lombok.Data;

@Data
public class LocationStatusDict {

    private String key;
    private String title;

    public LocationStatusDict(LocationStatus status) {
        this.key = status.name();
        this.title = status.getTitle();
    }
}
