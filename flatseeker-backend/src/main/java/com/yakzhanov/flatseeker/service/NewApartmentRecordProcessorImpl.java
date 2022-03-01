package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.service.newrecordprocessor.NewApartmentRecordConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewApartmentRecordProcessorImpl implements NewApartmentRecordProcessor {

    public static final int THREADS = 8;
    private final List<NewApartmentRecordConsumer> processors;

    @SuppressWarnings("FieldMayBeFinal")
    private ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS);

    public NewApartmentRecordProcessorImpl(List<NewApartmentRecordConsumer> processors) {
        this.processors = processors;
    }

    @Override
    public void process(ApartmentRecord newRecord) {
        processors.stream()
          .peek(processor -> log.info("Creating {} task for '{}'. Platform {}", processor.title(), newRecord.getTitle(), newRecord.getPlatformName()))
          .map(processor -> (Runnable) () -> processor.process(newRecord))
          .forEach(poolExecutor::submit);
    }
}
