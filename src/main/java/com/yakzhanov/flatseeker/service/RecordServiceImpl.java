package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final ApartmentRecordRepository repository;
    private final Map<String, AptPlatform> aptPlatformMap;

    @Override
    public Optional<List<ApartmentRecord>> loadAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<List<ApartmentRecord>> loadByPlatformName(String platformName) {
        return Optional.ofNullable(aptPlatformMap.get(platformName)) // checking if given platform exists
          .map(AptPlatform::name)
          .map(repository::findAllByPlatformName);
    }

}
