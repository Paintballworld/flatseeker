package com.yakzhanov.flatseeker.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import com.sun.istack.Nullable;
import com.yakzhanov.flatseeker.model.ApartmentRecord;
import com.yakzhanov.flatseeker.model.dict.ProcessStatus;
import com.yakzhanov.flatseeker.platform.AptPlatform;
import org.jsoup.nodes.Document;

public class AppUtils {

    @Nullable
    public static URL convertToUrlOrNull(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static ApartmentRecord parseRecord(Document document, AptPlatform aptPlatform, URL pageUrl, ProcessStatus processStatus) {
        return ApartmentRecord.builder()
          .id(UUID.randomUUID().toString())
          .title(aptPlatform.extractTitle(document))
          .link(pageUrl.toString())
          .description(aptPlatform.extractDescription(document))
          .rentPrice(aptPlatform.extractRentPrice(document))
          .feePrice(aptPlatform.extractFeePrice(document))
          .deposit(aptPlatform.extractDeposit(document))
          .area(aptPlatform.extractArea(document))
          .conditioner(aptPlatform.extractConditioner(document))
          .animalStatus(aptPlatform.extractAnimalStatus(document))
          .bathroomStatus(aptPlatform.extractBathroomStatus(document))
          .location(aptPlatform.extractLocation(document))
          .apartmentType(aptPlatform.extractApartmentType(document))
          .platformName(aptPlatform.name())
          .createdAt(aptPlatform.extractCreatedAt(document))
          .mainImageUrl(aptPlatform.extractMainImageUrl(document))
          .processStatus(processStatus)
          .build();
    }
}
