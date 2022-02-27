package com.yakzhanov.flatseeker.repository;

import com.yakzhanov.flatseeker.model.ApartmentRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRecordRepository extends JpaRepository<ApartmentRecord, String> {

    @Query("select title from ApartmentRecord where platformName = :platformName order by createdAt desc")
    Page<String> loadTitleOnly(Pageable pageable, String platformName);

}
