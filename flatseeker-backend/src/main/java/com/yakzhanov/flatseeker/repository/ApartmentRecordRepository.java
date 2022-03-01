package com.yakzhanov.flatseeker.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.ProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ApartmentRecordRepository extends JpaRepository<ApartmentRecord, String> {

    List<ApartmentRecord> findAllByPlatformNameOrderByInsertedAt(String platformName);

    Optional<ApartmentRecord> findFirstByTitle(String title);

    @Query("select link from ApartmentRecord where platformName = :platformName order by viewed, insertedAt desc, title desc")
    List<String> loadLinkOnly(String platformName);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord set processStatus = :newStatus where id = :id")
    void updateProcessStatus(String id, ProcessStatus newStatus);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord  set viewed = true where id = :id")
    void updateViewedStatus(String id);

}
