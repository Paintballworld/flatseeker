package com.yakzhanov.flatseeker.platform;

import java.net.URL;
import java.util.Date;
import java.util.List;
import com.yakzhanov.flatseeker.conf.AppParams;
import com.yakzhanov.flatseeker.model.AnimalsStatus;
import com.yakzhanov.flatseeker.model.ApartmentType;
import com.yakzhanov.flatseeker.model.BathroomStatus;
import com.yakzhanov.flatseeker.model.LinkData;
import org.jsoup.nodes.Document;

public interface AptPlatform {

    String baseUrl();

    boolean canSendRequest();

    AnimalsStatus extractAnimalStatus(Document document);

    ApartmentType extractApartmentType(Document document);

    Integer extractArea(Document document);

    BathroomStatus extractBathroomStatus(Document document);

    Boolean extractConditioner(Document document);

    Date extractCreatedAt(Document document);

    Integer extractDeposit(Document document);

    String extractDescription(Document document);

    Integer extractFeePrice(Document document);

    String extractLocation(Document document);

    String extractMainImageUrl(Document document);

    Integer extractRentPrice(Document document);

    List<LinkData> extractSearchPageLinks(Document document);

    String extractTitle(Document document);

    String name();

    int readTimeoutMillis();

    URL resolveSearchPageUrl(int pageNumber, AppParams params);

    int searchPageInitialValue();

    void updateRequestTime();

    void markAsExhausted();

}
