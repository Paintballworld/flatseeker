package com.yakzhanov.flatseeker.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ApartmentRecordRepository extends JpaRepository<ApartmentRecord, String> {

    @Query("select r from ApartmentRecord r where r.platformName = :platformName and r.removed = false")
    List<ApartmentRecord> loadAllForPlatform(String platformName);

    @Query("select r from ApartmentRecord r where r.removed = false and r.processStatus.key <> 'OUTDATED' order by r.viewed, r.insertedAt desc, r.title desc")
    List<ApartmentRecord> loadNotRemoved();

    Optional<ApartmentRecord> findByLink(String linkToResolve);

    Optional<ApartmentRecord> findFirstByTitle(String title);

    @Query("select link from ApartmentRecord where platformName = :platformName order by viewed, insertedAt desc, title desc")
    List<String> loadLinkOnly(String platformName);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord set processStatus = :newStatus where id = :id")
    void updateProcessStatus(String id, ProcessStatus newStatus);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord set removed = true where id = :id")
    void updateRemoveFlag(String id);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord set processStatus = :processStatusId where id = :id")
    void updateStatus(String id, Long processStatusId);

    @Modifying(flushAutomatically = true)
    @Query("update ApartmentRecord set viewed = true where id = :id")
    void updateViewedStatus(String id);

}
