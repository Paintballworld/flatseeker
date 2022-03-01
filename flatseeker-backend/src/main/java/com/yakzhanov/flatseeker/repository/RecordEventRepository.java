package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.RecordEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEventRepository extends JpaRepository<RecordEvent, String> {

    List<RecordEvent> findAllByApartmentRecordIdOrderByCreatedAtDesc(String id);

}
