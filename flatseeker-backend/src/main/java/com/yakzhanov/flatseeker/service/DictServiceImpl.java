package com.yakzhanov.flatseeker.service;

import static javax.management.timer.Timer.ONE_HOUR;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import com.yakzhanov.flatseeker.model.dict.AnimalStatus;
import com.yakzhanov.flatseeker.model.dict.ApartmentType;
import com.yakzhanov.flatseeker.model.dict.BathroomStatus;
import com.yakzhanov.flatseeker.model.dict.DictParent;
import com.yakzhanov.flatseeker.model.dict.LocationStatus;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.repository.AnimalStatusRepository;
import com.yakzhanov.flatseeker.repository.ApartmentTypeRepository;
import com.yakzhanov.flatseeker.repository.BathroomStatusRepository;
import com.yakzhanov.flatseeker.repository.LocationStatusRepository;
import com.yakzhanov.flatseeker.repository.ProcessStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

    public static final String NOT_DEFINED = "NOT_DEFINED";
    public static final String ALLOWED = "ALLOWED";
    public static final String NOT_ALLOWED = "NOW_ALLOWED";
    public static final String HOUSE = "HOUSE";
    public static final String FLAT = "FLAT";


    private final AnimalStatusRepository animalStatusRepository;
    private final ApartmentTypeRepository apartmentTypeRepository;
    private final BathroomStatusRepository bathroomStatusRepository;
    private final LocationStatusRepository locationStatusRepository;
    private final ProcessStatusRepository processStatusRepository;

    private final Map<Class<? extends DictParent>, DictParent> defaultValuesMap = new HashMap<>();
    private final Map<Class<? extends DictParent>, Map<String, ? extends DictParent>> cachedValues = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        updateCacheValues();
    }

    @Scheduled(fixedDelay = ONE_HOUR)
    private void updateCacheValues() {
        putToCache(animalStatusRepository.findAll());
        putToCache(apartmentTypeRepository.findAll());
        putToCache(bathroomStatusRepository.findAll());
        putToCache(locationStatusRepository.findAll());
        putToCache(processStatusRepository.findAll());
    }

    private <T extends DictParent> void putToCache(List<T> dictValues) {
        Class<? extends DictParent> dictClass = dictValues.iterator().next().getClass();
        cachedValues.put(dictClass, dictValues.stream().collect(Collectors.toMap(DictParent::getKey, Function.identity())));
        defaultValuesMap.put(dictClass, dictValues.iterator().next()); // assumed that first value is default
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DictParent> T getForKey(Class<T> dictionaryClass, String key) {
        return (T) cachedValues.getOrDefault(dictionaryClass, Collections.emptyMap()).get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DictParent> T defaultValue(Class<T> dictionaryClass) {
        return (T) defaultValuesMap.get(dictionaryClass);
    }

    @Override
    public Optional<List<AnimalStatus>> loadAnimalStatuses() {
        return Optional.of(animalStatusRepository.loadOrdered());
    }

    @Override
    public Optional<List<ApartmentType>> loadApartmentTypes() {
        return Optional.of(apartmentTypeRepository.loadOrdered());
    }

    @Override
    public Optional<List<BathroomStatus>> loadBathroomStatuses() {
        return Optional.of(bathroomStatusRepository.loadOrdered());
    }

    @Override
    public Optional<List<LocationStatus>> loadLocationStatuses() {
        return Optional.of(locationStatusRepository.loadOrdered());
    }

    @Override
    public Optional<List<ProcessStatus>> loadProcessStatuses() {
        return Optional.of(processStatusRepository.loadOrdered());
    }
}
