package com.yakzhanov.flatseeker.repository;

import com.yakzhanov.flatseeker.model.DuplicateRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplicateRecordRepository extends JpaRepository<DuplicateRecord, String> {
}
