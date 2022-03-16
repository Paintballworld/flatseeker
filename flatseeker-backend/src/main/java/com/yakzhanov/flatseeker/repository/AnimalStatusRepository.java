package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.AnimalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalStatusRepository extends JpaRepository<AnimalStatus, Long> {

    @Query("select dict from AnimalStatus dict order by dict.id")
    List<AnimalStatus> loadOrdered();
}
