package com.yakzhanov.flatseeker.service;

import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dto.LinkResolveRequest;

public interface LinkResolveService {

    void save(ApartmentRecord record);

    Optional<ApartmentRecord> tryToResolve(LinkResolveRequest request);
}
