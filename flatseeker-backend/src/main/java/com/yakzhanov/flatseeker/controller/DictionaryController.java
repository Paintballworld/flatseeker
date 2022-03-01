package com.yakzhanov.flatseeker.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.yakzhanov.flatseeker.model.AnimalsStatus;
import com.yakzhanov.flatseeker.model.ApartmentType;
import com.yakzhanov.flatseeker.model.BathroomStatus;
import com.yakzhanov.flatseeker.model.LocationStatus;
import com.yakzhanov.flatseeker.model.ProcessStatus;
import com.yakzhanov.flatseeker.model.dto.dict.AnimalStatusDict;
import com.yakzhanov.flatseeker.model.dto.dict.ApartmentTypeDict;
import com.yakzhanov.flatseeker.model.dto.dict.BathroomStatusDict;
import com.yakzhanov.flatseeker.model.dto.dict.LocationStatusDict;
import com.yakzhanov.flatseeker.model.dto.dict.ProcessStatusDict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dict")
public class DictionaryController {

    @GetMapping("/AnimalStatus")
    public ResponseEntity<List<AnimalStatusDict>> loadAnimalStatusDictValues() {
        return ResponseEntity.of(
          Optional.of(
            Arrays.stream(AnimalsStatus.values())
              .map(AnimalStatusDict::new)
              .collect(Collectors.toList())
          )
        );
    }

    @GetMapping("/ApartmentType")
    public ResponseEntity<List<ApartmentTypeDict>> loadApartmentTypeDictValues() {
        return ResponseEntity.of(
          Optional.of(
            Arrays.stream(ApartmentType.values())
              .map(ApartmentTypeDict::new)
              .collect(Collectors.toList())
          )
        );
    }

    @GetMapping("/BathroomStatus")
    public ResponseEntity<List<BathroomStatusDict>> loadBathroomStatusDictValues() {
        return ResponseEntity.of(
          Optional.of(
            Arrays.stream(BathroomStatus.values())
              .map(BathroomStatusDict::new)
              .collect(Collectors.toList())
          )
        );
    }

    @GetMapping("/LocationStatus")
    public ResponseEntity<List<LocationStatusDict>> loadLocationStatusDictValues() {
        return ResponseEntity.of(
          Optional.of(
            Arrays.stream(LocationStatus.values())
              .map(LocationStatusDict::new)
              .collect(Collectors.toList())
          )
        );
    }

    @GetMapping("/ProcessStatus")
    public ResponseEntity<List<ProcessStatusDict>> loadProcessStatusDictValues() {
        return ResponseEntity.of(
          Optional.of(
            Arrays.stream(ProcessStatus.values())
              .map(ProcessStatusDict::new)
              .collect(Collectors.toList())
          )
        );
    }
}
