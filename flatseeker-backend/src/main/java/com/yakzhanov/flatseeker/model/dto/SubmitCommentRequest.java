package com.yakzhanov.flatseeker.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class SubmitCommentRequest {

    @NotNull
    private String id;

    @NotNull
    private String comment;
}
