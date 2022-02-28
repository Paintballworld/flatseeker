package com.yakzhanov.flatseeker.controller;

import java.util.List;
import javax.validation.Valid;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.service.RecordProvider;
import com.yakzhanov.flatseeker.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class ManualTestRecordsController {

    private final RecordProvider recordProvider;
    private final RecordService recordService;

    @GetMapping("/")
    public ResponseEntity<List<ApartmentRecord>> loadAll() {
        return ResponseEntity.of(recordService.loadAll());
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<ApartmentRecord> loadOne(@PathVariable String recordId) {
        return ResponseEntity.of(recordService.loadById(recordId));
    }

    @PutMapping("/")
    public void updateRecord(@RequestBody @Valid ApartmentRecord record) {
        recordService.update(record);
    }

    @GetMapping("/platform/{platformName}")
    public ResponseEntity<List<ApartmentRecord>> loadForPlatform(@PathVariable String platformName) {
        return ResponseEntity.of(recordService.loadByPlatformName(platformName));
    }

    @GetMapping("/next")
    public ResponseEntity<ApartmentRecord> loadNextRecords() {
        return ResponseEntity.of(recordProvider.nextRecord());
    }
}
