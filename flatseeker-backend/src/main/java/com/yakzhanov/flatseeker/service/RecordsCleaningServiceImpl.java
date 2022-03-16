package com.yakzhanov.flatseeker.service;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import com.yakzhanov.flatseeker.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordsCleaningServiceImpl implements RecordsCleaningService {

    public static final String OUTDATED = "OUTDATED";
    private final ApartmentRecordRepository recordRepository;
    private final Map<String, AptPlatform> platformMap;
    private final DictService dictService;

    @Override
    public long removeOutdated() {
        return recordRepository.loadNotRemoved().stream()
          .filter(this::isOutdated)
          .map(ApartmentRecord::getId)
          .peek(this::markAsOutdated)
          .count();
    }

    private void markAsOutdated(String recordId) {
        var outDatedStatus = dictService.getForKey(ProcessStatus.class, OUTDATED);
        recordRepository.updateStatus(recordId, outDatedStatus.getId());
    }

    @SneakyThrows
    private boolean isOutdated(ApartmentRecord record) {
        var platform = platformMap.get(record.getPlatformName());
        var url = Objects.requireNonNull(AppUtils.convertToUrlOrNull(record.getLink()));
        try {
            var document = Jsoup.parse(url, platform.readTimeoutMillis());
            return platform.isOutdated(document);
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == HttpStatus.GONE.value()) {
                return true;
            }
            throw e;
        }
    }
}
