package com.yakzhanov.flatseeker.service.newrecordprocessor;

import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewApartmentRecordSaver implements NewApartmentRecordConsumer {

    private final ApartmentRecordRepository apartmentRecordRepository;

    @Override
    public String title() {
        return "Saver";
    }

    @Override
    public void process(ApartmentRecord newRecord) {
        log.info("Saving {}", newRecord.getTitle());
        apartmentRecordRepository.save(newRecord);
    }
}
