package com.yakzhanov.flatseeker.model.dto;

import javax.validation.constraints.NotNull;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class UpdateStatusRequest {

    @NotNull
    private String id;

    @NotNull
    private String newStatus;
}
