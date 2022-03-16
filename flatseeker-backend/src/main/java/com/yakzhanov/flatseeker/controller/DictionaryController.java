package com.yakzhanov.flatseeker.controller;

import java.util.List;
import com.yakzhanov.flatseeker.model.dict.AnimalStatus;
import com.yakzhanov.flatseeker.model.dict.ApartmentType;
import com.yakzhanov.flatseeker.model.dict.BathroomStatus;
import com.yakzhanov.flatseeker.model.dict.LocationStatus;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dict")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictService dictService;

    @GetMapping("/AnimalStatus")
    public ResponseEntity<List<AnimalStatus>> loadAnimalStatuses() {
        return ResponseEntity.of(dictService.loadAnimalStatuses());
    }

    @GetMapping("/ApartmentType")
    public ResponseEntity<List<ApartmentType>> loadApartmentTypes() {
        return ResponseEntity.of(dictService.loadApartmentTypes());
    }

    @GetMapping("/BathroomStatus")
    public ResponseEntity<List<BathroomStatus>> loadBathroomStatuses() {
        return ResponseEntity.of(dictService.loadBathroomStatuses());
    }

    @GetMapping("/LocationStatus")
    public ResponseEntity<List<LocationStatus>> loadLocationStatuses() {
        return ResponseEntity.of(dictService.loadLocationStatuses());
    }

    @GetMapping("/ProcessStatus")
    public ResponseEntity<List<ProcessStatus>> loadProcessStatuses() {
        return ResponseEntity.of(dictService.loadProcessStatuses());
    }
}
