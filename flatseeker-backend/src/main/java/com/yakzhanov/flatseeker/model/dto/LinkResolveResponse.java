package com.yakzhanov.flatseeker.model.dto;

import com.yakzhanov.flatseeker.model.ApartmentRecord;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class LinkResolveResponse {

    private ApartmentRecord record;
    private boolean alreadyExists;

    public static LinkResolveResponse ofNew(ApartmentRecord newRecord) {
        return LinkResolveResponse.builder()
          .record(newRecord)
          .alreadyExists(false)
          .build();
    }

    public static LinkResolveResponse ofExisting(ApartmentRecord existingRecord) {
        return LinkResolveResponse.builder()
          .record(existingRecord)
          .alreadyExists(true)
          .build();
    }
}
