package com.yakzhanov.flatseeker.service;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import com.yakzhanov.flatseeker.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordsCleaningServiceImpl implements RecordsCleaningService {

    private final ApartmentRecordRepository recordRepository;
    private final Map<String, AptPlatform> platformMap;

    @Override
    public long removeOutdated() {
        return recordRepository.loadNotRemoved().stream()
          .filter(this::isOutdated)
          .map(ApartmentRecord::getId)
          .peek(recordRepository::updateRemoveFlag)
          .count();
    }

    @SneakyThrows
    private boolean isOutdated(ApartmentRecord record) {
        var platform = platformMap.get(record.getPlatformName());
        var url = Objects.requireNonNull(AppUtils.convertToUrlOrNull(record.getLink()));
        var document = Jsoup.parse(url, platform.readTimeoutMillis());
        return platform.isOutdated(document);
    }
}
