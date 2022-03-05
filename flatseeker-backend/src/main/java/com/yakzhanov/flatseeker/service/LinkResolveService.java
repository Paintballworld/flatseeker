package com.yakzhanov.flatseeker.service;

import java.util.Optional;
import com.yakzhanov.flatseeker.model.dto.LinkResolveRequest;
import com.yakzhanov.flatseeker.model.dto.LinkResolveResponse;

public interface LinkResolveService {

    Optional<LinkResolveResponse> tryToResolve(LinkResolveRequest request);
}
