package com.yakzhanov.flatseeker.scheduler;

import com.yakzhanov.flatseeker.service.NewApartmentRecordProcessor;
import com.yakzhanov.flatseeker.service.RecordProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateNewRecordsScheduler {

    private final RecordProvider recordProvider;
    private final NewApartmentRecordProcessor newApartmentRecordProcessor;

    @Scheduled(fixedDelay = 60_000L, initialDelay = 2_000L)
    public void loadOneNewPageAndSave() {
        recordProvider.nextRecord()
          .ifPresentOrElse(newApartmentRecordProcessor::process, () -> log.info("No record loaded - skipping..."));
    }

}
