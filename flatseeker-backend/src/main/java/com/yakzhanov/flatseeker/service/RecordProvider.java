package com.yakzhanov.flatseeker.service;

import java.util.Optional;
import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface RecordProvider {

    Optional<ApartmentRecord> nextRecord();
}
