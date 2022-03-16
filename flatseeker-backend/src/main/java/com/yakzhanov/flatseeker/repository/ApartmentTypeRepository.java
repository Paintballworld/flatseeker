package com.yakzhanov.flatseeker.repository;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentTypeRepository extends JpaRepository<ApartmentType, Long> {

    @Query("select dict from ApartmentType dict order by dict.id")
    List<ApartmentType> loadOrdered();
}
