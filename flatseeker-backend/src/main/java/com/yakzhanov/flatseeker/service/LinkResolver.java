package com.yakzhanov.flatseeker.service;

import com.yakzhanov.flatseeker.model.ApartmentRecord;

public interface LinkResolver {

    ApartmentRecord resolve(String link);
}
