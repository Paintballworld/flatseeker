package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.DuplicateRecord;
import com.yakzhanov.flatseeker.model.ProcessStatus;
import com.yakzhanov.flatseeker.model.RecordEvent;
import com.yakzhanov.flatseeker.model.dto.SubmitCommentRequest;

public interface RecordService {

    Optional<List<ApartmentRecord>> loadAll();

    Optional<ApartmentRecord> loadById(String recordId);

    Optional<List<ApartmentRecord>> loadByPlatformName(String platformName);

    Optional<List<DuplicateRecord>> loadDuplicates(String originalRecordId);

    Optional<List<RecordEvent>> loadRecordEvents(String recordId);

    void saveNew(ApartmentRecord record);

    Optional<List<RecordEvent>> submitComment(SubmitCommentRequest request);

    void update(ApartmentRecord record);

    void updateStatus(String id, ProcessStatus newStatus);
}
