package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.BathroomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BathroomStatusRepository extends JpaRepository<BathroomStatus, Long> {

    @Query("select dict from BathroomStatus dict order by dict.id")
    List<BathroomStatus> loadOrdered();
}
