package com.yakzhanov.flatseeker.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LinkResolveRequest {

    @NotNull
    @NotEmpty
    private String urlToResolve;
}
