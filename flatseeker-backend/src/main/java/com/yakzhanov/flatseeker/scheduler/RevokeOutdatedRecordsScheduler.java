package com.yakzhanov.flatseeker.scheduler;

import com.yakzhanov.flatseeker.service.RecordsCleaningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RevokeOutdatedRecordsScheduler {

    private final RecordsCleaningService recordsCleaningService;

    @Scheduled(cron = "0 0 23 * * *")
    public void loadOneNewPageAndSave() {
        var removedCount = recordsCleaningService.removeOutdated();
        if (removedCount > 0) {
            log.info("{} records were removed as outdated", removedCount);
        } else {
            log.info("Nothing to remove");
        }
    }

}
