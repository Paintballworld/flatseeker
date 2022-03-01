package com.yakzhanov.flatseeker.model.dto.dict;

import com.yakzhanov.flatseeker.model.ProcessStatus;
import lombok.Data;

@Data
public class ProcessStatusDict {

    private String key;
    private String title;
    private String color;

    public ProcessStatusDict(ProcessStatus status) {
        this.key = status.name();
        this.title = status.getTitle();
        this.color = status.getColor();
    }
}
