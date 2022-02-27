package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRecordRepository extends JpaRepository<ApartmentRecord, String> {

    List<ApartmentRecord> findAllByPlatformName(String platformName);

    @Query("select title from ApartmentRecord where platformName = :platformName order by createdAt desc")
    List<String> loadTitleOnly(String platformName);

}
