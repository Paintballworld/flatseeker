package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.LocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationStatusRepository extends JpaRepository<LocationStatus, Long> {

    @Query("select dict from LocationStatus dict order by dict.id")
    List<LocationStatus> loadOrdered();
}
