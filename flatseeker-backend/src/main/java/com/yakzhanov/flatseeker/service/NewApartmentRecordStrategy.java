package com.yakzhanov.flatseeker.service;

import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface NewApartmentRecordStrategy {

    void process(ApartmentRecord newRecord);
}
