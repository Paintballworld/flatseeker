package com.yakzhanov.flatseeker.service;

import java.util.List;
import java.util.Optional;
import com.yakzhanov.flatseeker.model.dict.AnimalStatus;
import com.yakzhanov.flatseeker.model.dict.ApartmentType;
import com.yakzhanov.flatseeker.model.dict.BathroomStatus;
import com.yakzhanov.flatseeker.model.dict.LocationStatus;
import com.yakzhanov.flatseeker.model.dict.DictParent;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;

public interface DictService {

    <T extends DictParent> T getForKey(Class<T> dictionaryClass, String key);

    <T extends DictParent> T defaultValue(Class<T> dictionaryClass);

    Optional<List<AnimalStatus>> loadAnimalStatuses();

    Optional<List<ApartmentType>> loadApartmentTypes();

    Optional<List<BathroomStatus>> loadBathroomStatuses();

    Optional<List<LocationStatus>> loadLocationStatuses();

    Optional<List<ProcessStatus>> loadProcessStatuses();
}
