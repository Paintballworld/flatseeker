package com.yakzhanov.flatseeker.service.newrecordprocessor;

import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface NewApartmentRecordConsumer {

    String title();

    void process(ApartmentRecord newRecord);
}
