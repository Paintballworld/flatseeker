package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.DuplicateRecord;
import com.yakzhanov.flatseeker.model.ProcessStatus;
import com.yakzhanov.flatseeker.model.RecordEvent;
import com.yakzhanov.flatseeker.model.dto.SubmitCommentRequest;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import com.yakzhanov.flatseeker.repository.ApartmentRecordRepository;
import com.yakzhanov.flatseeker.repository.DuplicateRecordRepository;
import com.yakzhanov.flatseeker.repository.RecordEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final ApartmentRecordRepository recordRepository;
    private final RecordEventRepository eventRepository;
    private final DuplicateRecordRepository duplicateRecordRepository;
    private final Map<String, AptPlatform> aptPlatformMap;

    @Override
    public Optional<List<ApartmentRecord>> loadAll() {
        return Optional.of(recordRepository.findAll());
    }

    @Override
    public Optional<ApartmentRecord> loadById(String recordId) {
        return recordRepository.findById(recordId);
    }

    @Override
    public Optional<List<ApartmentRecord>> loadByPlatformName(String platformName) {
        return Optional.ofNullable(aptPlatformMap.get(platformName)) // checking if given platform exists
          .map(AptPlatform::name)
          .map(recordRepository::findAllByPlatformNameOrderByInsertedAt);
    }

    @Override
    public Optional<List<DuplicateRecord>> loadDuplicates(String originalRecordId) {
        return Optional.of(duplicateRecordRepository.findAllByOriginalRecordId(originalRecordId));
    }

    @Override
    public Optional<List<RecordEvent>> loadRecordEvents(String recordId) {
        return Optional.of(eventRepository.findAllByApartmentRecordIdOrderByCreatedAtDesc(recordId));
    }

    @Override
    public Optional<List<RecordEvent>> submitComment(SubmitCommentRequest request) {
        eventRepository.save(RecordEvent.forComment(request.getId(), request.getComment()));
        return Optional.of(eventRepository.findAllByApartmentRecordIdOrderByCreatedAtDesc(request.getId()));
    }

    @Override
    public void update(ApartmentRecord record) {
        recordRepository.save(record);
    }

    @Override
    public void updateStatus(String id, ProcessStatus newStatus) {
        recordRepository.updateProcessStatus(id, newStatus);
        eventRepository.save(RecordEvent.forNewStatus(id, newStatus));
    }

}
