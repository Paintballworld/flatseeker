package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface RecordService {

    Optional<List<ApartmentRecord>> loadAll();

    Optional<List<ApartmentRecord>> loadByPlatformName(String platformName);
}
