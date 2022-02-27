package com.yakzhanov.flatseeker.controller;

import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.service.RecordProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class ManualTestRecordsController {

    private final RecordProvider recordProvider;

    @GetMapping
    public ResponseEntity<ApartmentRecord> loadNextRecords() {
        return ResponseEntity.of(recordProvider.nextRecord());
    }
}
