package com.yakzhanov.flatseeker.service.newrecordprocessor;

import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.DuplicateRecord;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import com.yakzhanov.flatseeker.repository.DuplicateRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewApartmentRecordSaver implements NewApartmentRecordConsumer {

    private final ApartmentRecordRepository apartmentRecordRepository;
    private final DuplicateRecordRepository duplicateRecordRepository;

    @Override
    public String title() {
        return "Saver";
    }

    @Override
    public void process(ApartmentRecord newRecord) {
        log.info("Saving {}", newRecord.getTitle());
        apartmentRecordRepository.findFirstByTitle(newRecord.getTitle())
          .ifPresentOrElse(
            original -> processDuplicate(newRecord, original),
            () -> apartmentRecordRepository.save(newRecord)
          );
    }

    private void processDuplicate(ApartmentRecord newRecord, ApartmentRecord original) {
        log.info("Duplicate detected for title {}", newRecord.getTitle());
        duplicateRecordRepository.save(DuplicateRecord.duplicate(original));
        // TODO: 01.03.2022 Update original?
    }
}
