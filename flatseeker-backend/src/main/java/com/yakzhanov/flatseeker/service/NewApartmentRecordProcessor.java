package com.yakzhanov.flatseeker.service;

import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface NewApartmentRecordProcessor {

    void process(ApartmentRecord newRecord);
}
