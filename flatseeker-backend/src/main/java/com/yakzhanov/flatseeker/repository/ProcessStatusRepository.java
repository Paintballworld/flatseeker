package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStatusRepository extends JpaRepository<ProcessStatus, Long> {

    @Query("select dict from ProcessStatus dict order by dict.id")
    List<ProcessStatus> loadOrdered();
}
