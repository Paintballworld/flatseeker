package com.yakzhanov.flatseeker.controller;

import java.util.List;
import javax.validation.Valid;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.DuplicateRecord;
import com.yakzhanov.flatseeker.model.RecordEvent;
import com.yakzhanov.flatseeker.model.dto.SubmitCommentRequest;
import com.yakzhanov.flatseeker.model.dto.UpdateStatusRequest;
import com.yakzhanov.flatseeker.service.RecordProvider;
import com.yakzhanov.flatseeker.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordsController {

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

    @GetMapping("/events/{recordId}")
    public ResponseEntity<List<RecordEvent>> loadRecordEvents(@PathVariable String recordId) {
        return ResponseEntity.of(recordService.loadRecordEvents(recordId));
    }

    @PostMapping("/events/")
    public ResponseEntity<List<RecordEvent>> submitNewCommentAndRefresh(@RequestBody @Valid SubmitCommentRequest request) {
        return ResponseEntity.of(recordService.submitComment(request));
    }

    @GetMapping("/duplicates/{originalRecordId}")
    public ResponseEntity<List<DuplicateRecord>> loadDuplicates(@PathVariable String originalRecordId) {
        return ResponseEntity.of(recordService.loadDuplicates(originalRecordId));
    }


    @PutMapping("/update-status")
    public void updateStatus(@RequestBody @Valid UpdateStatusRequest request) {
        recordService.updateStatus(request.getId(), request.getNewStatus());
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
